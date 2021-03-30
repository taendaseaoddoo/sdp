package com.example.demo.controller;

import com.example.demo.bean.Information;
import com.example.demo.bean.User;
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
public class UserController {

    HashMap<String, Object> hashMap = new HashMap<>();

    @Autowired
    private UserService userService;

    @Autowired
    private InformationService informationService;

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
    public HashMap<String, Object> findUser(@RequestParam(value = "userUid") String userUid) {
        User user = userService.getUserByUid(userUid);
        if(user != null){
            hashMap.put("status", "success");
            hashMap.put("user", user);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getInfoList")
    public HashMap<String, Object> getInfoList(@RequestParam(value = "userUid") String userUid) {
        User user = userService.getUserByUid(userUid);
        if(user.getCollects() != null){
            hashMap.put("status", "success");
            hashMap.put("infoList", user.getCollects());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/addCollect")
    public void addCollect(@RequestParam(value = "userUid") String userUid,@RequestParam(value = "informationUid") String informationUid) {
        User user = userService.getUserByUid(userUid);
        Information information = informationService.getInformationByUid(informationUid);
        List<Information> infos = user.getCollects();
        infos.add(information);
        user.setCollects(infos);
        userService.addUser(user);
    }

    @PostMapping(value = "/removeCollect")
    public void removeCollect(@RequestParam(value = "userUid") String userUid,@RequestParam(value = "informationUid") String informationUid) {
        User user = userService.getUserByUid(userUid);
        Information information = informationService.getInformationByUid(informationUid);
        List<Information> infos = user.getCollects();
        infos.remove(information);
        user.setCollects(infos);
        userService.addUser(user);
    }

}