package com.example.demo.service;

import com.example.demo.bean.Information;
import com.example.demo.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository = null;

    @Transactional
    public Information getInformationByUid(String informationUid) { return informationRepository.getOne(informationUid); }

}
