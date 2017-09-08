package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by chenzhi on 2017/9/7.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUserInfo(){
        User user=userDao.findUserInfo();
        //User user=null;
        return user;
    }
}
