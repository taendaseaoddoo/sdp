package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.CommentService;
import com.example.demo.service.IndentService;
import com.example.demo.service.InformationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    HashMap<String, Object> hashMap = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private IndentService indentService;

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addUser(@RequestParam(value = "user") User user) {
        userService.addUser(user);
        if(user != null){
            hashMap.put("status", "success");
            hashMap.put("userId", user.getUserId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/modify")
    public void modifyUser(@RequestParam(value = "user") User user) {
        userService.addUser(user);
    }

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findUser(@RequestParam(value = "userId") String userId) {
        User user = userService.getUserById(userId);
        if(user != null){
            hashMap.put("status", "success");
            hashMap.put("user", user);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getInfoList")
    public HashMap<String, Object> getInfoList(@RequestParam(value = "userId") String userId) {
        User user = userService.getUserById(userId);
        if(user.getCollects() != null){
            hashMap.put("status", "success");
            hashMap.put("infoList", user.getCollects());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/addCollect")
    public void addCollect(@RequestParam(value = "userId") String userId,@RequestParam(value = "informationId") String informationId) {
        User user = userService.getUserById(userId);
        Information information = informationService.getInformationById(informationId);
        List<Information> infos = user.getCollects();
        infos.add(information);
        user.setCollects(infos);
        userService.addUser(user);
    }

    @PostMapping(value = "/removeCollect")
    public void removeCollect(@RequestParam(value = "userId") String userId,@RequestParam(value = "informationId") String informationId) {
        User user = userService.getUserById(userId);
        Information information = informationService.getInformationById(informationId);
        List<Information> infos = user.getCollects();
        infos.remove(information);
        user.setCollects(infos);
        userService.addUser(user);
    }

    @GetMapping(value = "/getRate")
    public HashMap<String, Object> getRate(@RequestParam(value = "userId") String userId) {
        List<Indent> indents = indentService.getIndentByStatusAndUser("已完成", userId);
        int total = indents.size();
        List<Comment> comments = commentService.getCommentByUser(userId);
        int good = comments.size();
        double rate = 0.0;
        if(total != 0){
            rate = (good/total) * 100;
        }else{
        }
        if(indents != null && comments != null){
            hashMap.put("status", "success");
            hashMap.put("rate", rate);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}