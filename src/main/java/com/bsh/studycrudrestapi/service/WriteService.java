package com.bsh.studycrudrestapi.service;

import com.bsh.studycrudrestapi.entities.AttachmentEntity;
import com.bsh.studycrudrestapi.entities.ImageEntity;
import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.mapper.WriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Service
public class WriteService {

    private final WriteMapper writeMapper;

    @Autowired
    public WriteService(WriteMapper writeMapper) {
        this.writeMapper = writeMapper;
    }

    @Transactional
    public boolean putWrite(HttpServletRequest request, WriteEntity writeEntity, MultipartFile[] files) throws IOException {

        writeEntity.setCreatedAt(new Date())
                .setViews(0L);

        if(this.writeMapper.insertWrite(writeEntity)<1){
            return false;
        }

        if(files.length==0){
            return false;
        }
        int inserted =0;
        for(MultipartFile file : files){
            AttachmentEntity attachment = new AttachmentEntity()
                    .setArticleIndex(writeEntity.getIndex())
                    .setFileData(file.getBytes())
                    .setFileContentType(file.getContentType())
                    .setFileSize(file.getSize())
                    .setFileName(file.getOriginalFilename());
            inserted += this.writeMapper.insertAttachment(attachment);
        }
        return inserted == files.length || this.writeMapper.insertWrite(writeEntity)>0;
    }


    public ImageEntity putImage(HttpServletRequest request, MultipartFile file) throws IOException {
        ImageEntity image = new ImageEntity()
                .setName(file.getOriginalFilename())
                .setSize(file.getSize())
                .setContentType(file.getContentType())
                .setData(file.getBytes())
                .setCreatedAt(new Date())
                .setClientIp(request.getRemoteAddr())
                .setClientUa(request.getHeader("User-Agent"));
        this.writeMapper.insertImage(image);
        return image;
    }

    public ImageEntity getImage(int index){
        return this.writeMapper.selectImage(index);
    }


}
