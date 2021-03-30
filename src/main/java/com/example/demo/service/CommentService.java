package com.example.demo.service;

import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository = null;

}
