package com.demo.dao.sys;

import com.demo.model.sys.User;

import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/10 0010.
 */
public interface UserDao {
    public void insert(User user);
    public void update(User user);
    public void delete(String id);
    public User get(Map<String,Object> map);
    public List<User> select(User user);
    public void updatePassword(User user);
}
