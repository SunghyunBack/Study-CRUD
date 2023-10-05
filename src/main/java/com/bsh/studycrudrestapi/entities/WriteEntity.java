package com.bsh.studycrudrestapi.entities;

import java.util.Date;
import java.util.Objects;

public class WriteEntity {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WriteEntity that = (WriteEntity) o;
        return index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }

    public int getIndex() {
        return index;
    }

    public WriteEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public WriteEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public WriteEntity setCategory(String category) {
        this.category = category;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public WriteEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getViews() {
        return views;
    }

    public WriteEntity setViews(Long views) {
        this.views = views;
        return this;
    }

    private int index;
    private String title;
    private String category;
    private String content;

    public String getContent() {
        return content;
    }

    public WriteEntity setContent(String content) {
        this.content = content;
        return this;
    }

    private Date createdAt;
    private Long views;

}
