package com.example.demo.repository;

import com.example.demo.bean.Tag;
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
public interface TagRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

    List<Tag> getAllByParentIdEquals(String str);

    @Modifying
    @Query("delete from tag t where t.tagId = ?1")
    int deleteByTagId(String tagId);
}
