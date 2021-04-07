package com.example.demo.controller;

import com.example.demo.bean.Information;
import com.example.demo.bean.Tag;
import com.example.demo.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/information")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findInformation(@RequestParam(value = "informationId") String informationId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Information information = informationService.getInformationById(informationId);
        if(information != null){
            hashMap.put("status", "success");
            hashMap.put("information", information);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/add")
    public HashMap<String, Object> addInformation(@RequestParam(value = "information") Information information) {
        HashMap<String, Object> hashMap = new HashMap<>();
        informationService.addInformation(information);
        if(information != null){
            hashMap.put("status", "success");
            hashMap.put("informationId", information.getInformationId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getAllAvailable")
    public HashMap<String, Object> getAvailableInformation() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Information> informations = informationService.getInformationAvailable();
        if(informations != null){
            hashMap.put("status", "success");
            hashMap.put("informations", informations);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/screen")
    public HashMap<String, Object> screenInformation(@RequestParam("location") String location, @RequestParam("tags") List<Tag> tags) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Information> informations = informationService.getInformationByCondition(location, tags);
        if(informations != null){
            hashMap.put("status", "success");
            hashMap.put("informations", informations);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/modify")
    public HashMap<String, Object> modifyInformation(@RequestParam(value = "informationId") String informationId, @RequestParam(value = "status") String status) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Information information = informationService.getInformationById(informationId);
        information.setStatus(status);
        informationService.addInformation(information);
        if(information != null){
            hashMap.put("status", "success");
            hashMap.put("informationId", informationId);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getAvailableByUser")
    public HashMap<String, Object> getAvailableByUser(@RequestParam(value = "userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Information> informations = informationService.getAvailableByUser(userId);
        if(informations != null){
            hashMap.put("status", "success");
            hashMap.put("informations", informations);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/modifyIsDelete")
    public HashMap<String, Object> modifyIsDelete(@RequestParam(value = "informationId") String informationId, @RequestParam(value = "type") int type) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Information information = informationService.getInformationById(informationId);
        information.setIsDelete(type);
        informationService.addInformation(information);
        if(information != null){
            hashMap.put("status", "success");
            hashMap.put("informationId", informationId);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}