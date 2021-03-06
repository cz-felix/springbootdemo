package com.demo.utils.shiro;

import com.demo.model.sys.Resources;
import com.demo.model.sys.User;
import com.demo.service.sys.ResourcesService;
import com.demo.service.sys.UserService;
import com.demo.utils.common.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenzhi on 2017/11/11 0011.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userId = (String) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userId",userId);
        List<Resources> resourcesList = resourcesService.loadUserResources(map);
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Resources resources: resourcesList){
            info.addStringPermission(resources.getResUrl());
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据用户名去查找对象
        User user = userService.selectByUsername(token.getUsername());
        if(user==null) throw new UnknownAccountException();
        if (Constant.INVALID_STATUS.equals(user.getStatus())) {
            throw new LockedAccountException(); // 帐号锁定
        }
        if(user != null) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),getName());

            //更新登录时间 last login time
            user.setLastLoginTime(new Date());
            userService.updateLastLoginTime(user);

            // 当验证都通过后，把用户信息放在session里
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("userSession", user);
            session.setAttribute("userSessionId", user.getId());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
