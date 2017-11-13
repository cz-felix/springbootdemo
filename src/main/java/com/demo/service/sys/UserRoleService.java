package com.demo.service.sys;

import com.demo.dao.sys.UserRoleDao;
import com.demo.model.sys.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void addUserRole(UserRole userRole) {
        //删除
        userRoleDao.deleteByUserId(userRole.getUserId());
        //添加
        String[] roleids = userRole.getRoleId().split(",");
        for (String roleId : roleids) {
            UserRole u = new UserRole();
            u.setUserId(userRole.getUserId());
            u.setRoleId(roleId);
            userRoleDao.insert(u);
        }

    }
}
