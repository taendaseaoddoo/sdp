package com.example.demo.controller;

import com.example.demo.bean.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
public class CommentController {

    HashMap<String, Object> hashMap = new HashMap<>();

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addComment(@RequestParam(value = "comment")Comment comment) {
        commentService.addComment(comment);
        if(comment != null){
            hashMap.put("status", "success");
            hashMap.put("commentId", comment.getCommentId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getByIndent")
    public HashMap<String, Object> getCommentByIndent(@RequestParam(value = "indentId") String indentId) {
        Comment comment = commentService.getCommentByIndent(indentId);
        if(comment != null){
            hashMap.put("status", "success");
            hashMap.put("comment", comment);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findComment(@RequestParam("commentId") String commentId) {
        Comment comment = commentService.getCommentById(commentId);
        if(comment != null){
            hashMap.put("status", "success");
            hashMap.put("comment", comment);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getGood")
    public HashMap<String, Object> getGoodComment(@RequestParam("evaluatedId") String evaluatedId) {
        List<Comment> comments = commentService.getGoodComment(evaluatedId);
        if(comments != null){
            hashMap.put("status", "success");
            hashMap.put("comments", comments);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getBad")
    public HashMap<String, Object> getBadComment(@RequestParam("evaluatedId") String evaluatedId) {
        List<Comment> comments = commentService.getBadComment(evaluatedId);
        if(comments != null){
            hashMap.put("status", "success");
            hashMap.put("comments", comments);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/numOfGood")
    public HashMap<String, Object> getNumOfGood(@RequestParam("evaluatedId") String evaluatedId) {
        List<Comment> comments = commentService.getGoodComment(evaluatedId);
        int num = comments.size();
        if(comments != null){
            hashMap.put("status", "success");
            hashMap.put("num", num);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/numOfBad")
    public HashMap<String, Object> getNumOfBad(@RequestParam("evaluatedId") String evaluatedId) {
        List<Comment> comments = commentService.getBadComment(evaluatedId);
        int num = comments.size();
        if(comments != null){
            hashMap.put("status", "success");
            hashMap.put("num", num);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}