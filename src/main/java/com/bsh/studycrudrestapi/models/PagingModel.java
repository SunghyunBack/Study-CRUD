package com.bsh.studycrudrestapi.models;

public class PagingModel {
    public final int pageCount;
    public final int totalCount;
    public final int requestPage;
    public final int minPage;
    public final int maxPage;
    public final int displayStartPage;
    public final int displayEndPage;
    public final int offset;

    public PagingModel(int pageCount, int totalCount, int requestPage){
        this.pageCount = pageCount;
        this.totalCount=totalCount;
        this.requestPage = requestPage;
        this.minPage =1;
        this.maxPage = totalCount/pageCount + (totalCount%pageCount ==0?0:1);
        this.displayStartPage = ((requestPage-1)/20)*20+1;
        this.displayEndPage = Math.min(this.maxPage, ((requestPage-1)/20)*20+20);
        this.offset=(requestPage-1)*pageCount;
    }


}
