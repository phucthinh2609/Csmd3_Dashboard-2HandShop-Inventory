package com.mvpt.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class Product {
    private Integer id;
    private String title;
    private String image;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private Integer updatedBy;
    private String content;


    public Product() {
    }

    public Product(Integer id, String title, String image, Date createdAt, Integer createdBy, Date updatedAt, Integer updatedBy, String content) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.content = content;
    }

    public Product(String title, String image, String content) {
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public Product(Integer id, String title, String image, String content) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotEmpty(message = "Title must not be empty")

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @NotEmpty(message = "Image must not be empty")
    @Pattern(regexp = "[a-z\\-_0-9\\/\\:\\.]*\\.(jpg|jpeg|png|gif)",
            message = "Image needs to follow the correct image url pattern. Ex: 'img-1.png'")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

