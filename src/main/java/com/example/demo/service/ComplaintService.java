package com.example.demo.service;

import com.example.demo.bean.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository = null;

    @Transactional
    public void addComplaint(Complaint complaint) { complaintRepository.save(complaint); }

    @Transactional
    public void deleteComplaint(String complaintId) { complaintRepository.deleteByComplaintId(complaintId); }

    @Transactional
    public List<Complaint> getAllComplaint() { return complaintRepository.findAll(); }

    @Transactional
    public List<Complaint> getAllByUser(String userId) { return complaintRepository.getAllByUserIdEquals(userId); }

    @Transactional
    public Complaint getComplaintById(String complaintId) { return complaintRepository.getOne(complaintId); }

    @Transactional
    public List<Complaint> getComplaintByStatus(String status) { return complaintRepository.getAllByStatusEquals(status); }

}
