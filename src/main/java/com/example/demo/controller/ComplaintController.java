package com.example.demo.controller;

import com.example.demo.bean.Complaint;
import com.example.demo.bean.Information;
import com.example.demo.bean.User;
import com.example.demo.service.CommentService;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.InformationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addComplaint(@RequestParam(value = "type")String type,@RequestParam(value = "reason")String reason) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Complaint complaint=new Complaint(type,reason);
        if(complaint != null){
            hashMap.put("status", "success");
            complaintService.addComplaint(complaint);
            hashMap.put("complaintId", complaint.getComplaintId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getOne")
    public HashMap<String, Object> findComplaint(@RequestParam(value = "complaintId") String complaintId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Complaint complaint = complaintService.getComplaintById(complaintId);
        if(complaint != null){
            hashMap.put("status", "success");
            hashMap.put("complaint", complaint);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @DeleteMapping(value = "/delete")
    public HashMap<String, Object> deleteComplaint(@RequestParam(value = "complaintId") String complaintId) {
        HashMap<String, Object> hashMap = new HashMap<>();
//        String complaintId = complaint.getComplaintId();
        complaintService.deleteComplaint(complaintId);
        if(complaintId != ""){
            hashMap.put("status", "success");
            hashMap.put("complaintId", complaintId);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getAll")
    public HashMap<String, Object> getAllComplaint() {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Complaint> complaints = complaintService.getAllComplaint();
        if(complaints != null){
            hashMap.put("status", "success");
            hashMap.put("complaints", complaints);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getByUser")
    public HashMap<String, Object> getComplaintByUser(@RequestParam(value = "userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Complaint> complaints = complaintService.getAllByUser(userId);
        if(complaints != null){
            hashMap.put("status", "success");
            hashMap.put("complaints", complaints);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/modifyStatus")
    public HashMap<String, Object> modifyComplaintStatus(@RequestParam(value = "complaintId") String complaintId, @RequestParam(value = "status") String status) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Complaint complaint = complaintService.getComplaintById(complaintId);
        complaint.setStatus(status);
        complaintService.addComplaint(complaint);
        if(complaint != null){
            hashMap.put("status", "success");
            hashMap.put("complaintId", complaint.getComplaintId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value  = "/getByStatus")
    public HashMap<String, Object> getComplaintByStatus(@RequestParam("status") String status) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Complaint> complaints = complaintService.getComplaintByStatus(status);
        if(complaints != null){
            hashMap.put("status", "success");
            hashMap.put("complaints", complaints);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/myComplaint")
    public HashMap<String, Object> getMyComplaint(@RequestParam("userId") String userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<Complaint> complaints = complaintService.getAllByUser(userId);
        for(Complaint complaint : complaints){
            HashMap<String, Object> map1 = new HashMap<>();
            String informationId = complaint.getInformationId();
            Information information = informationService.getInformationById(informationId);
            String userId1 = information.getUserId();
            User user = userService.getUserById(userId1);
            map1.put("user", user);
            map1.put("complaint", complaint);
            map1.put("information", information);
            list.add(map1);
        }
        if(list != null){
            hashMap.put("status", "success");
            hashMap.put("complaints", list);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}
