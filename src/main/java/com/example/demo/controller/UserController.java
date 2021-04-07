package com.example.demo.controller;

import com.example.demo.bean.*;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private IndentService indentService;

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addUser(@RequestParam(value = "userName") String userName,@RequestParam(value = "gender") char gender,@RequestParam(value = "studentId") String studentId,
                                           @RequestParam(value = "department") String department,@RequestParam(value = "major") String major,
                                           @RequestParam(value = "wechatId") String wechatId,@RequestParam(value = "phoneNum") String phoneNum) {
        HashMap<String, Object> hashMap = new HashMap<>();
        User user=new User(userName,gender,studentId,department,major,wechatId,phoneNum,0,"","user");
        if(user != null){
            hashMap.put("status", "success");
            userService.addUser(user);
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
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userService.getUserById(userId);
        if(user != null){
            hashMap.put("status", "success");
            hashMap.put("user", user);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getByWechat")
    public HashMap<String, Object> getUserByWechat(@RequestParam(value = "wechatId") String wechatId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userService.getUserByWechat(wechatId);
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Indent> indents = indentService.getIndentByUserAndStatus(userId, "已完成");
        int total = indents.size();
        List<Comment> comments = commentService.getGoodComment(userId);
        int good = comments.size();
        double rate = 0.0;
        int rateInt = 0;
        if(total != 0){
            rate = ((double)good/(double)total) * 100;
            rateInt = (int) rate;
        }else{
        }
        if(indents != null && comments != null){
            hashMap.put("status", "success");
            hashMap.put("rate", rateInt);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getCollections")
    public HashMap<String, Object> getAllCollections(@RequestParam(value = "userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<HashMap<String, Object>> list = new ArrayList<>();
        User user = userService.getUserById(userId);
        List<Information> collects = user.getCollects();
        for(Information collect : collects){
            HashMap<String, Object> map1 = new HashMap<>();
            User user1 = userService.getUserById(collect.getUserId());
            map1.put("user", user1);
            map1.put("information", collect);
            list.add(map1);
        }
        if(list != null){
            hashMap.put("status", "success");
            hashMap.put("collections", list);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}