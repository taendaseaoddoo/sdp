package com.example.demo.service;

import com.example.demo.bean.Information;
import com.example.demo.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class InformationService {

    @Autowired
    private InformationRepository informationRepository = null;

    @Transactional
    public Information getInformationById(String informationId) { return informationRepository.getOne(informationId); }

    @Transactional
    public void addInformation(Information information) {
        information.setDeadline(new Date());
        informationRepository.save(information);
    }

    @Transactional
    public List<Information> getInformationAvailable() {
        return informationRepository.getAllByIsDeleteEquals(0);
    }

}
