package com.demo.dao.sys;

import com.demo.model.sys.Resources;
import com.demo.model.sys.User;

import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
public interface ResourcesDao {
    public void insert(Resources resources);
    public void update(Resources resources);
    public void delete(String id);
    public Resources get(String id);
    public List<Resources> select(Resources resources);
    public List<Resources> loadUserResources(Map<String,Object> map);
    public List<Resources> queryResourcesListWithSelected(String roleId);
}
