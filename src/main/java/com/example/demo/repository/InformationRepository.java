package com.example.demo.repository;

import com.example.demo.bean.Information;
import com.example.demo.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.sound.sampled.Line;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Repository
public interface InformationRepository extends JpaRepository<Information, String>, JpaSpecificationExecutor<Information> {

    Information findInformationByInformationId(String informationId);

    List<Information> getAllByIsDeleteEquals(int isDeleted);

    List<Information> getAllByLocationEqualsAndTagsIn(String location, List<Tag> tags);

    List<Information> getAllByIsDeleteEqualsAndUserIdEquals(int isDeleted, String userId);

    List<Information> getAllByUserIdEqualsAndStatusIn(String userId, List<String> status);

}
