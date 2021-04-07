package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yanmaoyuan on 2018/4/15.
 */
@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 36)
    private String tagId;

    //标签所属父ID
    private String parentId;

    //标签名
    private String tagName;

    //标签描述
    private String description;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        tagId = tagId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
