package com.example.shiro.controller;


import com.example.shiro.vo.TuserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class Login {

    @RequestMapping(value = "/login")
    public String login(@RequestBody  TuserVo tuserVo) {
        if(!StringUtils.hasText(tuserVo.getUserCode())||!StringUtils.hasText(tuserVo.getPassword())){
            log.warn("用户名{}和密码{}为空",tuserVo.getUserCode(),tuserVo.getPassword());
            return "用户名和密码不能为空";

        }
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(tuserVo.getUserCode(),tuserVo.getPassword());
        try{
            subject.login(token);

        }catch (UnknownAccountException unknownAccountException){
            return "用户名对应的账号不存在";
        }catch (AuthenticationException authenticationException){
            return "用户名和密码不对";
        }catch (AuthorizationException authorizationException){
            return "用户没有权限";
        }

        return "login sucess!!!";
    }
    @RequiresRoles("select")
    @RequestMapping(value = "/role")
    public String testRole(){
        return "test role";
    }

    @RequiresPermissions("user:select")
    @RequestMapping(value = "/permiss")
    public String testPermiss(){
        return "test permiss";
    }

    @RequiresGuest
    @RequestMapping(value = "/guest")
    public String guest(@RequestBody  TuserVo tuserVo){
        return "test guest";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/permissUpdate")
    public String permissUpdate(){
        return "test permissUpdate";
    }


    @RequestMapping(value = "/testnopre")
    public String testnopre(){
        return "test testnopre";
    }

}
