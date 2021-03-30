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

    @Transactional
    public User getUserByUid(String userUid) { return userRepository.getOne(userUid); }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

}
