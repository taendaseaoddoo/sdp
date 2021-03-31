package com.example.demo.controller;

import com.example.demo.bean.Information;
import com.example.demo.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
public class InformationController {

    HashMap<String, Object> hashMap = new HashMap<>();

    @Autowired
    private InformationService informationService;

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findInformation(@RequestParam(value = "informationId") String informationId) {
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
        List<Information> informations = informationService.getInformationAvailable();
        if(informations != null){
            hashMap.put("status", "success");
            hashMap.put("informations", informations);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }




}