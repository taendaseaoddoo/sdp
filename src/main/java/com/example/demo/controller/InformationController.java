package com.example.demo.controller;

import com.example.demo.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
public class InformationController {

    @Autowired
    private InformationService informationService;

}