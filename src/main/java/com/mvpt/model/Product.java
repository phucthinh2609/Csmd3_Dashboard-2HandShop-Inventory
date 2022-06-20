package com.mvpt.model;

import java.net.URL;
import java.util.Date;

public class Product {
    private Long id;
    private String title;
    private URL image;
    private Date createdAt;
    private Date updatedAt;
    private String content;

    public Product() {
    }

    public Product(Long id, String title, Date createdAt, Date updatedAt, String content) {
        this.id = id;
        this.title = title;

        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public Product(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s",
                id,
                title,
                createdAt,
                updatedAt,
                content
        );
    }
}

