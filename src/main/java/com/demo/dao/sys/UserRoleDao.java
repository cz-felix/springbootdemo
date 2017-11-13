package com.demo.dao.sys;

import com.demo.model.sys.UserRole;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
public interface UserRoleDao {

    public void insert(UserRole userRole);
    public void deleteByUserId(String userId);
}
