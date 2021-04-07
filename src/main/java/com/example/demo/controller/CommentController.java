package com.example.demo.controller;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Indent;
import com.example.demo.bean.Information;
import com.example.demo.bean.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.IndentService;
import com.example.demo.service.InformationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndentService indentService;

    @Autowired
    private InformationService informationService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addComment(@RequestParam(value = "comment") Comment comment) {
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Comment> comments = commentService.getCommentByIndent(indentId);
        if(comments != null){
            hashMap.put("status", "success");
            hashMap.put("comments", comments);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getMyIndent")
    public HashMap<String, Object> getCommentByIndent(@RequestParam(value = "indentId") String indentId, @RequestParam(value = "evaluatedId") String evaluatedId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Comment> comments = commentService.getCommentByIndent(indentId);
        int count=0;
        for(int i=0;i<comments.size();i++) {
            if (comments.get(i).getEvaluatedId().equals(evaluatedId)) {
                count++;
                hashMap.put("status", "success");
                hashMap.put("comment", comments.get(i));
            }
        }
        if(count==0)
            hashMap.put("status","failure");

        return hashMap;
    }

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findComment(@RequestParam("commentId") String commentId) {
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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

    @GetMapping(value = "/getAllComments")
    public HashMap<String, Object> getAllComments(@RequestParam("userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<Comment> comments = commentService.getAllComments(userId);
        for(Comment comment : comments){
            HashMap<String, Object> map1 = new HashMap<>();
            String evaluatorId = comment.getEvaluatorId();
            User user = userService.getUserById(evaluatorId);
            String indentId = comment.getIndentId();
            Indent indent = indentService.getIndentById(indentId);
            String informationId = indent.getInformationId();
            Information information = informationService.getInformationById(informationId);
            map1.put("user", user);
            map1.put("comment", comment);
            map1.put("information", information);
            list.add(map1);
        }
        if(list != null){
            hashMap.put("status", "success");
            hashMap.put("comments", list);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}