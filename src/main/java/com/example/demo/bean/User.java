package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/15.
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 36)
    private String userId;

    //用户姓名
    private String userName;

    //用户性别
    private String gender;

    //用户学号
    private String studentId;

    //用户所属学院
    private String department;

    //用户所在专业
    private String major;

    //用户微信号
    private String wechatId;

    //用户手机号
    private String phoneNum;

    //用户获得好评数
    private int favorableNum;

    //用户头像
    private String avatar;

    //用户角色
    private String role;

    //用户收藏列表
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Information> collects;

    public User(){}


    public User(String userName, String gender, String studentId,String department,String major,String wechatId,String phoneNum,int favorableNum,String avatar,String role){
        this.userName=userName;
        this.gender=gender;
        this.studentId=studentId;
        this.department=department;
        this.major=major;
        this.wechatId=wechatId;
        this.phoneNum=phoneNum;
        this.favorableNum=favorableNum;
        this.avatar=avatar;
        this.role=role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getFavorableNum() {
        return favorableNum;
    }

    public void setFavorableNum(int favorableNum) {
        this.favorableNum = favorableNum;
    }

    public List<Information> getCollects() {
        return collects;
    }

    public void setCollects(List<Information> collects) {
        this.collects = collects;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
