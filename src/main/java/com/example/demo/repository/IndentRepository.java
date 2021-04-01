package com.example.demo.repository;

import com.example.demo.bean.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Repository
public interface IndentRepository extends JpaRepository<Indent, String>, JpaSpecificationExecutor<Indent> {

    List<Indent> getAllByInformationIdEquals(String informationId);

    List<Indent> getAllByApplicationIdEqualsAndStatusEquals(String applicantId, String status);

    List<Indent> getAllByApplicationIdEqualsAndStatusNot(String applicantId, String status);

    List<Indent> getAllByApplicationIdEqualsOrPublisherIdEqualsAndStatusEquals(String userId, String userid, String status);

}
