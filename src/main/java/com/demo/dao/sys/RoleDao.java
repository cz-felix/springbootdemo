package com.demo.dao.sys;

import com.demo.model.sys.Role;
import com.demo.model.sys.User;

import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
public interface RoleDao {
    public void insert(Role role);
    public void update(Role role);
    public void delete(String id);
    public Role get(String id);
    public List<Role> select(Role role);
    public List<Role> queryRoleListWithSelected(String userId);
}
