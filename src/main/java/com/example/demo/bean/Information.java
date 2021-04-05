package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.*;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/15.
 */
@Entity
@Table(name="information")
public class Information {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 36)
    private String informationId;

    //发布者ID
    private String userId;

    //信息标题
    private String title;

    //信息位置
    private String location;

    //标签
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    private List<Tag> tags;

    //信息详情
    private String description;

    //价格
    private double price;

    //信息有效时间
    private Date deadline;

    //"组团"类型所需人数
    private int memberCount;

    //"组团"类型目前剩余人数
    private int memberRemain;

    //信息状态
    private String status;

    //图片链接
    private String imageUrl;

    //收藏信息的用户列表
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();

    //信息是否删除
    private int isDelete;

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getMemberRemain() {
        return memberRemain;
    }

    public void setMemberRemain(int memberRemain) {
        this.memberRemain = memberRemain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
