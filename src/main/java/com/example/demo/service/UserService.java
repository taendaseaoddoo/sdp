package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yanmaoyuan on 2018/4/16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository = null;

    @javax.transaction.Transactional
    public void save(User user){
        userRepository.save(user);
    }

    @Transactional
    public User getUserById(String userId) { return userRepository.findUserByUserId(userId); }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getUserByWechat(String wechatId) { return userRepository.getByWechatIdEquals(wechatId); }


}
