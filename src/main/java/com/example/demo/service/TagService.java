package com.example.demo.service;

import com.example.demo.bean.Tag;
import com.example.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository = null;

    @Transactional
    public void addTag(Tag tag) { tagRepository.save(tag); }

    @Transactional
    public void deleteTag(String tagId) { tagRepository.deleteByTagId(tagId); }

    @Transactional
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Transactional
    public List<Tag> getAllParentTag() {
        return tagRepository.getAllByParentIdEquals("NoParent");
    }

    @Transactional
    public List<Tag> getChildTag(String parentId) {
        return tagRepository.getAllByParentIdEquals(parentId);
    }
}
