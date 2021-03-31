package com.example.demo.controller;

import com.example.demo.bean.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
public class TagController {

    HashMap<String, Object> hashMap = new HashMap<>();

    @Autowired
    private TagService tagService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addTag(@RequestParam(value = "tag") Tag tag) {
        tagService.addTag(tag);
        if(tag != null){
            hashMap.put("status", "success");
            hashMap.put("tag", tag.getTagId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/delete")
    public HashMap<String, Object> deleteTag(@RequestParam(value = "tag") Tag tag) {
        String tagId = tag.getTagId();
        tagService.deleteTag(tagId);
        if(tag != null){
            hashMap.put("status", "success");
            hashMap.put("tag", tagId);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @PostMapping(value = "/modify")
    public HashMap<String, Object> modifyTag(@RequestParam(value = "tag") Tag tag) {
        tagService.addTag(tag);
        if(tag != null){
            hashMap.put("status", "success");
            hashMap.put("tag", tag.getTagId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getAll")
    public HashMap<String, Object> getAllTag() {
        List<Tag> tags = tagService.getAllTag();
        if(tags != null){
            hashMap.put("status", "success");
            hashMap.put("tags", tags);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getAllParentTag")
    public HashMap<String, Object> getAllParentTag() {
        List<Tag> tags = tagService.getAllParentTag();
        if(tags != null){
            hashMap.put("status", "success");
            hashMap.put("tags", tags);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @GetMapping(value = "/getChildTag")
    public HashMap<String, Object> getChildTag(@RequestParam(value = "parentTag") Tag tag) {
        String parentId = tag.getParentId();
        List<Tag> tags = tagService.getChildTag(parentId);
        if(tags != null){
            hashMap.put("status", "success");
            hashMap.put("tags", tags);
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

}