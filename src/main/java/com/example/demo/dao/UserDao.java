package com.example.demo.dao;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by chenzhi on 2017/9/7.
 */
public interface UserDao {

    public User findById(String id);

    public User getUser(@Param("email")String email, @Param("password")String password);

    public void deleteUser(String id);
}
