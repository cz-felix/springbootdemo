package com.demo.service.sys;

import com.demo.dao.sys.UserDao;
import com.demo.dao.sys.UserRoleDao;
import com.demo.model.sys.User;
import com.demo.utils.common.Constant;
import com.demo.utils.common.PasswordHelper;
import com.demo.utils.common.Result;
import com.demo.utils.common.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/10 0010.
 */
@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 分页查询用户列表
     * @param page
     * @param limit
     * @param user
     * @return
     */
    public PageInfo<User> findPage(Integer page, Integer limit, User user){
        if(page > 0 && limit > 0){
            PageHelper.startPage(page, limit);
        }
        List<User> userList = userDao.select(user);
        PageInfo<User> pageInfo = new PageInfo(userList);
        return pageInfo;
    }

    /**
     * 新增用户信息
     * @param user
     */
    @Transactional
    public void insert(User user){
        try {
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            Date date = new Date();
            user.setId(UUIDGenerator.uuid());
            user.setCreateTime(date);
            String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
            user.setCreateNameId(userid);
            user.setLastUpdateNameId(userid);
            user.setLastUpdateTime(date);
            userDao.insert(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 新增用户信息
     * @param user
     */
    @Transactional
    public void update(User user){
        try {
            String userid = (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
            user.setLastUpdateNameId(userid);
            user.setLastUpdateTime(new Date());
            userDao.update(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public User selectByUsername(String username){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("username",username);
        User user = userDao.get(map);
        return user;
    }

    /**
     * 根据ID获取用户信息
     * @param id
     * @return
     */
    public User getById(String id){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("id",id);
        User user = userDao.get(map);
        return user;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delUser(String userId) {
        //删除用户表
        userDao.delete(userId);
        //删除用户角色表
        userRoleDao.deleteByUserId(userId);
    }

    @Transactional
    public void batchDel(String[] ids){
        for (String id : ids) {
            this.delUser(id);
        }
    }
}
