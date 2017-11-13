package com.demo.service.sys;

import com.demo.dao.sys.RoleResourcesDao;
import com.demo.model.sys.RoleResources;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
@Service
public class RoleResourcesService {

    @Autowired
    private RoleResourcesDao roleResourcesDao;

    //更新权限
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    @CacheEvict(cacheNames="resources", allEntries=true)
    public void addRoleResources(RoleResources roleResources) {
        //删除
        roleResourcesDao.deleteByRoleId(roleResources.getRoleId());
        //添加
        if(StringUtils.isNotEmpty(roleResources.getResourcesId())){
            String[] resourcesArr = roleResources.getResourcesId().split(",");
            for(String resourcesId:resourcesArr ){
                RoleResources r = new RoleResources();
                r.setRoleId(roleResources.getRoleId());
                r.setResourcesId(resourcesId);
                roleResourcesDao.insert(r);
            }
        }
    }
}
