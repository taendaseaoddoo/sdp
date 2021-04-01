package com.example.demo.controller;

import com.example.demo.bean.Indent;
import com.example.demo.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/indent")
class IndentController {

    HashMap<String, Object> hashMap = new HashMap<>();

    @Autowired
    private IndentService indentService;

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findIndent(@RequestParam(value = "indentId") String indentId) {
        Indent indent = indentService.getIndentById(indentId);
        if(indent != null){
            hashMap.put("status", "success");
            hashMap.put("indent", indent);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/add")
    public HashMap<String, Object> addIndent(@RequestParam(value = "indent") Indent indent) {
        indentService.addIndent(indent);
        if(indent != null){
            hashMap.put("status", "success");
            hashMap.put("indentId", indent.getIndentId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getByInformation")
    public HashMap<String, Object> getByInformation(@RequestParam(value = "informationId") String informationId) {
        List<Indent> indents = indentService.getIndentByInformationId(informationId);
        if(indents != null){
            hashMap.put("status", "success");
            hashMap.put("indents", indents);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getDone")
    public HashMap<String, Object> getByStatus(@RequestParam(value = "applicantId") String applicantId) {
        String status = "已完成";
        List<Indent> indents = indentService.getIndentByStatus(applicantId, status);
        if(indents != null){
            hashMap.put("status", "success");
            hashMap.put("indents", indents);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getIncomplete")
    public HashMap<String, Object> getByStatusIn(@RequestParam(value = "applicantId") String applicantId) {
        String status = "已完成";
        List<Indent> indents = indentService.getIndentByStatusNot(applicantId, status);
        if(indents != null){
            hashMap.put("status", "success");
            hashMap.put("indents", indents);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/updateTime")
    public void updateModifyTime(@RequestParam(value = "indentId") String indentId) {
        Indent indent = indentService.getIndentById(indentId);
        indent.setModifyTime(new Date());
        indentService.addIndent(indent);
    }

    @PostMapping(value = "/updateStatus")
    public void updateStatus(@RequestParam(value = "indentId") String indentId, @RequestParam(value = "status") String status) {
        Indent indent = indentService.getIndentById(indentId);
        indent.setStatus(status);
        indentService.addIndent(indent);
    }



}
