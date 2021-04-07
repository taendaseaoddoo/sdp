package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Entity
@Table(name="indent")
public class Indent {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 36)
    private String indentId;

    //供需信息ID
    private String informationId;

    //信息发布者ID
    private String publisherId;

    //申请者ID
    private String applicationId;

    //订单状态
    private String status;

    //价格
    private double price;

    //订单详情
    private String description;

    //发布时间
    private Date applyTime;

    //申请时间
    private Date modifyTime;

    //保证完成时间
    private Date guaranteTime;

    public Indent(){}

    public Indent(double price,String description,Date modifyTime,Date guaranteTime){
        this.price=price;
        this.description=description;
        this.modifyTime=modifyTime;
        this.guaranteTime=guaranteTime;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getGuaranteTime() {
        return guaranteTime;
    }

    public void setGuaranteTime(Date guaranteTime) {
        this.guaranteTime = guaranteTime;
    }
}

