package com.example.demo.service;

import com.example.demo.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository = null;

}
