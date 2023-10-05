package com.bsh.studycrudrestapi.mapper;

import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.models.PagingModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MainMapper {

    WriteEntity[] selectAll();


    int selectCount(@Param(value="searchQuery")String searchQuery,
                    @Param(value = "category") String category);

    WriteEntity[] selectByPage(@Param(value="pagingModel")PagingModel pagingModel,
                               @Param(value="searchQuery")String searchQuery,
                               @Param(value = "category") String category);


}
