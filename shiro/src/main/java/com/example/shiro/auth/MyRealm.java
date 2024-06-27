package com.example.shiro.auth;

import com.example.shiro.service.TuserService;
import com.example.shiro.vo.TuserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Slf4j
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

        String password =new String ( ((UsernamePasswordToken) authenticationToken).getPassword()) ;
        log.info("doGetAuthenticationInfo username {} password {}",username,password);
       TuserVo vo=tuserService.getUserInfo(username);
       if(null==vo){
           return null;
       }else {
         //  vo.setPassword("");
           return new SimpleAuthenticationInfo(username,vo.getPassword(), ByteSource.Util.bytes(vo.getSalt()),getName());
       }

    }

    /**
     * 加密方式 二
     * @param authenticationToken
     * @param authenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) throws AuthenticationException {
       String password = null;
       String username = (String) authenticationToken.getPrincipal();

        if(authenticationToken instanceof UsernamePasswordToken ){
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            password=new String(token.getPassword());

        }
        if(!StringUtils.hasText(password)&&!StringUtils.hasText(username)){
            throw new AuthenticationException("用户名和密码不能为空");
        }
        TuserVo tuserVo=tuserService.getUserInfo(username);
        if(null==tuserVo){
            throw new AuthenticationException("用户不存在");

        }
         password=new SimpleHash("MD5",password,tuserVo.getSalt(),10).toHex();

         log.info("assertCredentialsMatch username {} password {} database password {}",username,password,tuserVo.getPassword());

         if(!password.equals(tuserVo.getPassword())){
             throw new AuthenticationException("用户密码不对");
         }




    }

}
