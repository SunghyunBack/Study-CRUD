package com.bsh.studycrudrestapi.service;

import com.bsh.studycrudrestapi.entities.AttachmentEntity;
import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.enums.PatchWriteResult;
import com.bsh.studycrudrestapi.mapper.BulletinMapper;
import com.bsh.studycrudrestapi.mapper.WriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Patch;
import java.net.URLEncoder;
import java.util.Date;

@Service
public class BulletinService {


    private  final BulletinMapper bulletinMapper;
    @Autowired
    public BulletinService(BulletinMapper bulletinMapper) {
        this.bulletinMapper = bulletinMapper;
    }


    public WriteEntity selectArticle(int index){
        WriteEntity article = this.bulletinMapper.selectArticleIndex(index);
        bulletinMapper.updateView(index);
        return article;
    }

    public AttachmentEntity[] getAttachmentsOf(WriteEntity write){
        return  this.bulletinMapper.selectAttachmentsByArticleIndexNoData(write.getIndex());
    }

    public AttachmentEntity getAttachment(int index){
        return this.bulletinMapper.selectAttachment(index);
    }
    public boolean deleteArticle(int index){
        return this.bulletinMapper.deleteArticle(index) >0;
    }

    public WriteEntity getPatchBulletin(int index){
        return this.bulletinMapper.selectArticleByPatchIndex(index);
    }

    public PatchWriteResult updateArticle(WriteEntity write){
        write.setCreatedAt(new Date());
        return this.bulletinMapper.updateArticleByIndex(write) >0
                ? PatchWriteResult.SUCCESS
                : PatchWriteResult.FAILURE;
    }
    public boolean updateViews(int index){
       return  this.bulletinMapper.updateView(index) > 0
                ? true
               : false;
    }



}
