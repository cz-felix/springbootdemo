package com.demo.service.sys;

import com.demo.dao.sys.ResourcesDao;
import com.demo.model.sys.Resources;
import com.demo.utils.common.UUIDGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.UUDecoder;

import java.util.List;
import java.util.Map;

/**
 * 资源业务类
 * Created by chenzhi on 2017/11/11 0011.
 */
@Service
public class ResourcesService {

    @Autowired
    private ResourcesDao resourcesDao;

    /**
     * 分页查询列表
     * @param page
     * @param limit
     * @param resources
     * @return
     */
    public PageInfo<Resources> findPage(Integer page, Integer limit, Resources resources){
        if(page > 0 && limit > 0){
            PageHelper.startPage(page, limit);
        }
        List<Resources> resourcesList = resourcesDao.select(resources);
        PageInfo<Resources> pageInfo = new PageInfo(resourcesList);
        return pageInfo;
    }

    @Transactional
    public void save(Resources resources){
        resources.setId(UUIDGenerator.uuid());
        resourcesDao.insert(resources);
    }

    public void delete(String id){
        resourcesDao.delete(id);
    }

    /**
     * 查询所有资源
     * @return
     */
    public List<Resources> queryAll(){
        Resources resources = new Resources();
        List<Resources>  resourcesList = resourcesDao.select(resources);
        return  resourcesList;
    }

    @Cacheable(cacheNames="resources",key="#map['userId'].toString()+#map['type']")
    public List<Resources> loadUserResources(Map<String, Object> map) {
        return resourcesDao.loadUserResources(map);
    }

    public List<Resources> queryResourcesListWithSelected(String roleId) {
        return resourcesDao.queryResourcesListWithSelected(roleId);
    }

}
