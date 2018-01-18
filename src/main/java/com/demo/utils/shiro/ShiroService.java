package com.demo.utils.shiro;

import com.demo.model.sys.Resources;
import com.demo.service.sys.ResourcesService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
@Service
public class ShiroService {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/layui/**","anon");
        filterChainDefinitionMap.put("/loginInfo.html","anon");
        filterChainDefinitionMap.put("/css/login.css","anon");
        List<Resources> resourcesList = resourcesService.queryAll();
        for(Resources resources:resourcesList){
            if (StringUtils.isNotEmpty(resources.getResUrl())) {
                String permission = "perms[" + resources.getResUrl()+ "]";
                filterChainDefinitionMap.put(resources.getResUrl(),permission);
            }
        }
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            System.out.println("更新权限成功！！");
        }
    }
}
