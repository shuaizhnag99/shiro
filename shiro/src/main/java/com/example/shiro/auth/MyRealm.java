package com.example.shiro.auth;

import com.example.shiro.service.TuserService;
import com.example.shiro.vo.TuserVo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    private TuserService tuserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        TuserVo vo=tuserService.getUserInfo(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if(!CollectionUtils.isEmpty(vo.getTroleList())){
            vo.getTroleList().stream().forEach(ro ->{
                authorizationInfo.addRole(ro.getRoleCode());
            });

        }

        if(!CollectionUtils.isEmpty(vo.getRosurceList())){
            vo.getRosurceList().stream().forEach(ro ->{
                authorizationInfo.addStringPermission(ro.getResourceCode());
            });
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       if(!StringUtils.hasText((String) authenticationToken.getPrincipal())){
          return null;
       }
       String username = (String) authenticationToken.getPrincipal();
       TuserVo vo=tuserService.getUserInfo(username);
       if(null==vo){
           return null;
       }else {
         //  vo.setPassword("");
           return new SimpleAuthenticationInfo(username,vo.getPassword(),getName());
       }

    }

}
