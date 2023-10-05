package com.bsh.studycrudrestapi.mapper;

import com.bsh.studycrudrestapi.entities.AttachmentEntity;
import com.bsh.studycrudrestapi.entities.WriteEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BulletinMapper {



    WriteEntity selectArticleIndex(@Param(value="index") int index);

    AttachmentEntity[] selectAttachmentsByArticleIndexNoData(@Param(value="articleIndex")int articleIndex);

    AttachmentEntity selectAttachment(@Param(value="index")int index);

    int deleteArticle(@Param(value="index")int index);

    WriteEntity selectArticleByPatchIndex(@Param(value="index")int index);

int updateArticleByIndex(WriteEntity write);

    int updateView(@Param(value="index")int index);

}
