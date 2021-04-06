package com.example.demo.controller;

import com.example.demo.bean.Indent;
import com.example.demo.bean.Information;
import com.example.demo.bean.User;
import com.example.demo.service.IndentService;
import com.example.demo.service.InformationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/indent")
class IndentController {

    @Autowired
    private IndentService indentService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findIndent(@RequestParam(value = "indentId") String indentId) {
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<Indent> indents = indentService.getIndentByInformationId(informationId);
        for(Indent indent : indents){
            HashMap<String, Object> map1 = new HashMap<>();
            String applicationId = indent.getApplicationId();
            User user = userService.getUserById(applicationId);
            map1.put("user", user);
            map1.put("indent", indent);
            list.add(map1);
        }
        if(list != null){
            hashMap.put("status", "success");
            hashMap.put("indents", list);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getDone")
    public HashMap<String, Object> getByStatus(@RequestParam(value = "applicantId") String applicantId) {
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
//        HashMap<String, Object> hashMap = new HashMap<>();
        Indent indent = indentService.getIndentById(indentId);
        indent.setModifyTime(new Date());
        indentService.addIndent(indent);
    }

    @PostMapping(value = "/updateStatus")
    public void updateStatus(@RequestParam(value = "indentId") String indentId, @RequestParam(value = "status") String status) {
        Indent indent = indentService.getIndentById(indentId);
        if(status == "完成订单"){
            String oldStatus = indent.getStatus();
            if(oldStatus != "单方完成") {
                indent.setStatus("单方完成");
                indentService.addIndent(indent);
                String informationId = indent.getInformationId();
                Information information = informationService.getInformationById(informationId);
                information.setStatus("单方完成");
                informationService.addInformation(information);
            }else{
                indent.setStatus("已完成");
                indentService.addIndent(indent);
                String informationId = indent.getInformationId();
                Information information = informationService.getInformationById(informationId);
                information.setStatus("已完成");
                informationService.addInformation(information);
            }
        }else if(status == "签约"){
            indent.setStatus("签约");
            indentService.addIndent(indent);
            String informationId = indent.getInformationId();
            Information information = informationService.getInformationById(informationId);
            information.setStatus("履约中");
            informationService.addInformation(information);
            List<Indent> indents = indentService.getIndentByInformationId(informationId);
            for(int i = indents.size() - 1; i >= 0; i--){
                if(indents.get(i).getIndentId() == indent.getIndentId()){
                    indents.remove(i);
                }else{
                    indents.get(i).setStatus("回绝");
                    indentService.addIndent(indents.get(i));
                }
            }
        }else{
            indent.setStatus(status);
            indentService.addIndent(indent);
        }
    }

    @GetMapping(value = "/getAll")
    public HashMap<String, Object> getAll(@RequestParam(value = "userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        List<String> statusApply = new ArrayList<>();
        statusApply.add("申请中");
        statusApply.add("签约");
        statusApply.add("回绝");
        statusApply.add("单方完成");
        List<Indent> indentApply = indentService.getIndentByIdAndStatus(userId, statusApply);
        List<HashMap<String, Object>> listApply = new ArrayList<>();
        for(int i = 0; i < indentApply.size(); i++){
            HashMap<String, Object> map1 = new HashMap<>();
            Indent indent = indentApply.get(i);
            String informationId = indent.getInformationId();
            Information information = informationService.getInformationById(informationId);
            String applyUserId = information.getUserId();
            User user = userService.getUserById(applyUserId);
            map1.put("user", user);
            map1.put("information", information);
            map1.put("indent", indent);
            listApply.add(map1);
        }
        hashMap.put("apply", listApply);

        String status = "已完成";
        List<Indent> indentHistory = indentService.getIndentByUserAndStatus(userId, status);
        List<HashMap<String, Object>> listHistory = new ArrayList<>();
        for(Indent indent : indentHistory) {
            HashMap<String, Object> map2 = new HashMap<>();
            String informationId = indent.getInformationId();
            Information information = informationService.getInformationById(informationId);
            String applyUserId = information.getUserId();
            User user = userService.getUserById(applyUserId);
            map2.put("user", user);
            map2.put("information", information);
            map2.put("indent", indent);
            listHistory.add(map2);
        }
        hashMap.put("history", listHistory);

        List<String> statusRelease = new ArrayList<>();
        statusRelease.add("待申请");
        statusRelease.add("履约中");
        statusRelease.add("单方完成");
        List<Information> informationRelease = informationService.getByUserAndStatus(userId, statusRelease);
        List<HashMap<String, Object>> listRelease = new ArrayList<>();
        for(Information information : informationRelease) {
            HashMap<String, Object> map3 = new HashMap<>();
            User user = userService.getUserById(userId);
            map3.put("user", user);
            map3.put("information", information);
            listRelease.add(map3);
        }
        hashMap.put("release", listRelease);

        return hashMap;
    }

}
