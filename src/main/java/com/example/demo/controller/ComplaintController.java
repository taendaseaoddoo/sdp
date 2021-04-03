package com.example.demo.controller;

import com.example.demo.bean.Complaint;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/add")
    public HashMap<String, Object> addComplaint(@RequestParam(value = "complaint") Complaint complaint) {
        HashMap<String, Object> hashMap = new HashMap<>();
        complaintService.addComplaint(complaint);
        if(complaint != null){
            hashMap.put("status", "success");
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
    public HashMap<String, Object> deleteComplaint(@RequestParam(value = "complaint") Complaint complaint) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String complaintId = complaint.getComplaintId();
        complaintService.deleteComplaint(complaintId);
        if(complaint != null){
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

}
