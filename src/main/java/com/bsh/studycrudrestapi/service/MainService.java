package com.bsh.studycrudrestapi.service;

import com.bsh.studycrudrestapi.entities.WriteEntity;
import com.bsh.studycrudrestapi.mapper.MainMapper;
import com.bsh.studycrudrestapi.models.PagingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    public static final int PAGE_COUNT=20;
    private final MainMapper mainMapper;
    @Autowired
    public MainService(MainMapper mainMapper){
        this.mainMapper = mainMapper;
    }


    public WriteEntity[] getAll(){
        return this.mainMapper.selectAll();
    }

    public int getCount( String searchQuery,String category){
        return this.mainMapper.selectCount(searchQuery,category);
    }

    public WriteEntity[] getByPage(PagingModel pagingModel, String searchQuery,String category){
        WriteEntity[] write = this.mainMapper.selectByPage(pagingModel,searchQuery,category);
        return write;
    }


}
