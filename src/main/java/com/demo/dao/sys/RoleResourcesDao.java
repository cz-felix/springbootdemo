package com.demo.dao.sys;

import com.demo.model.sys.RoleResources;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
public interface RoleResourcesDao {

    public void insert(RoleResources roleResources);
    public void deleteByRoleId(String roleId);
}
