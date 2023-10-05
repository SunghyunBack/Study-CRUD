package com.bsh.studycrudrestapi.mapper;

import com.bsh.studycrudrestapi.entities.AttachmentEntity;
import com.bsh.studycrudrestapi.entities.ImageEntity;
import com.bsh.studycrudrestapi.entities.WriteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface WriteMapper {

    int insertWrite(WriteEntity write);

    int insertImage(ImageEntity image);

    int insertAttachment(AttachmentEntity attachment);

    ImageEntity selectImage(@Param(value="index")int index);
}
