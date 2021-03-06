package com.example.demo.repository;

import com.example.demo.bean.Indent;
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
public interface IndentRepository extends JpaRepository<Indent, String>, JpaSpecificationExecutor<Indent> {

    @Modifying
    @Query("delete from Indent i where i.indentId = ?1")
    int deleteByIndentId(String indentId);

    Indent findIndentByIndentId(String indentId);

    List<Indent> getAllByInformationIdEquals(String informationId);

    List<Indent> getAllByApplicationIdEqualsAndStatusEquals(String applicantId, String status);

    List<Indent> getAllByApplicationIdEqualsAndStatusNot(String applicantId, String status);

//    List<Indent> getAllByApplicationIdEqualsOrPublisherIdEqualsAndStatusEquals(String userId, String userid, String status);

    List<Indent> getAllByPublisherIdEqualsAndStatusEquals(String publisherId, String status);

    List<Indent> getAllByApplicationIdEqualsAndStatusIn(String userId, List<String> status);

//    List<Indent> getAllByApplicationIdEqualsOrPublisherIdEqualsAndStatusIn(String userId, String userid, List<String> status);

    List<Indent> getAllByInformationIdEqualsAndStatusNot(String informationId, String status);

}
