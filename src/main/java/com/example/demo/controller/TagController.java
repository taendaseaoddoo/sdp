package com.example.demo.controller;

import com.example.demo.bean.Tag;
import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@RestController
@RequestMapping(value = "/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping(value = "/add")
    public HashMap<String, Object> addTag(@RequestParam(value = "tagName") String tagName,@RequestParam(value = "description") String description) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Tag tag=new Tag(tagName,description);
        if(tag != null){
            hashMap.put("status", "success");
            tagService.addTag(tag);
            hashMap.put("tag", tag.getTagId());
        }else{
            hashMap.put("status", "failure");
        }
        return hashMap;
    }

    @DeleteMapping(value = "/delete")
    public HashMap<String, Object> deleteTag(@RequestParam(value = "tag") Tag tag) {
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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
        HashMap<String, Object> hashMap = new HashMap<>();
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