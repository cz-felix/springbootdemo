package com.demo.service.sys;

import com.demo.dao.sys.RoleDao;
import com.demo.dao.sys.RoleResourcesDao;
import com.demo.model.sys.Resources;
import com.demo.model.sys.Role;
import com.demo.utils.common.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色
 * Created by chenzhi on 2017/11/11 0011.
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleResourcesDao roleResourcesDao;

    /**
     * 分页查询角色列表
     * @param page
     * @param limit
     * @param role
     * @return
     */
    public PageInfo<Role> findPage(Integer page, Integer limit, Role role){
        if(page > 0 && limit > 0){
            PageHelper.startPage(page, limit);
        }
        List<Role> roleList = roleDao.select(role);
        PageInfo<Role> pageInfo = new PageInfo(roleList);
        return pageInfo;
    }

    public Role get(String id){
        return roleDao.get(id);
    }

    @Transactional
    public void add(Role role){
        role.setId(UUIDGenerator.uuid());
        roleDao.insert(role);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delRole(String roleId) {
        //删除角色
        roleDao.delete(roleId);
        //删除角色资源
        roleResourcesDao.deleteByRoleId(roleId);
    }

    public List<Role> queryRoleListWithSelected(String userId) {
        return roleDao.queryRoleListWithSelected(userId);
    }
}
