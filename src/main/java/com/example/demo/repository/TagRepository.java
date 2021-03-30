package com.example.demo.repository;

import com.example.demo.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String>, JpaSpecificationExecutor<Tag> {

}
