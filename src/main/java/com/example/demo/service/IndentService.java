package com.example.demo.service;

import com.example.demo.bean.Indent;
import com.example.demo.repository.IndentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class IndentService {

    @Autowired
    private IndentRepository indentRepository = null;

    @Transactional
    public Indent getIndentById(String indentId) { return indentRepository.getOne(indentId); }

    @Transactional
    public void addIndent(Indent indent) {
        indent.setApplyTime(new Date());
        indent.setModifyTime(new Date());
        indentRepository.save(indent);
    }

    @Transactional
    public List<Indent> getIndentByInformationId(String informationId) {
        return indentRepository.getAllByInformationIdEquals(informationId);
    }

    @Transactional
    public List<Indent> getIndentByStatus(String applicantId, String status) {
        return indentRepository.getAllByApplicationIdEqualsAndStatusEquals(applicantId, status);
    }

    @Transactional
    public List<Indent> getIndentByStatusNot(String applicantId, String status) {
        return indentRepository.getAllByApplicationIdEqualsAndStatusNot(applicantId, status);
    }

    @Transactional
    public List<Indent> getIndentByStatusAndUser(String status, String userId) {
        return indentRepository.getAllByApplicationIdEqualsOrPublisherIdEqualsAndStatusEquals(userId, userId,status);
    }

}
