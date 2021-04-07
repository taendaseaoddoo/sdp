package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yanmaoyuan on 2018/4/15.
 */
@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 36)
    private String commentId;

    //订单ID
    private String indentId;

    //评价者ID
    private String evaluatorId;

    //被评价者ID
    private String evaluatedId;

    //评价时间
    private Date createTime;

    //评价类型
    private int commentType;

    //评价理由
    private String reason;

    public Comment(){}

    public Comment(String reason,String indentId,int commentType){
        this.indentId=indentId;
        this.reason=reason;
        this.commentType=commentType;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getIndentId() {
        return indentId;
    }

    public void setIndentId(String indentId) {
        this.indentId = indentId;
    }

    public String getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(String evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public String getEvaluatedId() {
        return evaluatedId;
    }

    public void setEvaluatedId(String evaluatedId) {
        this.evaluatedId = evaluatedId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
