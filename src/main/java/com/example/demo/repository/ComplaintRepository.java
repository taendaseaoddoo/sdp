package com.example.demo.repository;

import com.example.demo.bean.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, String>, JpaSpecificationExecutor<Complaint> {

    @Modifying
    @Query("delete from complaint com where com.complaintId = ?1")
    int deleteByComplaintId(String complaintId);

    List<Complaint> getAllByUserIdEquals(String userId);

    List<Complaint> getAllByStatusEquals(String status);

}
