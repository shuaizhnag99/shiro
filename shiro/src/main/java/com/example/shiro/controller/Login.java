package com.example.shiro.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.shiro.bean.Tuser;
import com.example.shiro.service.TuserService;
import com.example.shiro.util.ResPonseResult;
import com.example.shiro.util.ShiroConstants;
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
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Controller
public class Login {
    @Autowired
    TuserService tuserService;

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");

        Map map=  httpServletRequest.getParameterMap();
        String userCode,password;
        if(map.isEmpty()){
            return   modelAndView;
        }
          userCode=httpServletRequest.getParameter("userCode");
          password=httpServletRequest.getParameter("password");
        log.info("login begin...");
        TuserVo tuserVo =new TuserVo();
        tuserVo.setUserCode(userCode);
        tuserVo.setPassword(password);
        if(StringUtils.isEmpty(tuserVo.getUserCode())||StringUtils.isEmpty(tuserVo.getPassword())){

            return   modelAndView;
        }else{


            return loginForm(tuserVo);
        }

    }

    @RequestMapping(value = "/loginForm")
    public ModelAndView loginForm(@RequestBody TuserVo tuserVo) {
         ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        if(!StringUtils.hasText(tuserVo.getUserCode())||!StringUtils.hasText(tuserVo.getPassword())){
            log.warn("用户名{}和密码{}为空",tuserVo.getUserCode(),tuserVo.getPassword());
            modelAndView.addObject("msg","用户名和密码不能为空");
            return  modelAndView;

        }
        String msg="login sucess!!!";
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(tuserVo.getUserCode(),tuserVo.getPassword());
        try{
            subject.login(token);

        }catch (UnknownAccountException unknownAccountException){
            modelAndView.setViewName("login");
             msg="用户名对应的账号不存在";

        }catch (AuthenticationException authenticationException){
            modelAndView.setViewName("login");
            msg="用户名和密码不对";

        }catch (AuthorizationException authorizationException){
            modelAndView.setViewName("login");
             msg="用户没有权限";


        }catch (Exception exception){
            modelAndView.setViewName("login");
            exception.printStackTrace();
            msg="后台异常";
        }
        modelAndView.addObject("msg",msg);
        return modelAndView;
    }


    @RequiresRoles("select")
    @RequestMapping(value = "/role")
    public ModelAndView testRole(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","the login has role can operate");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequiresPermissions("user:select")
    @RequestMapping(value = "/permiss")
    public ModelAndView testPermiss(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","the login has permiss user:select can operate");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequiresGuest
    @RequestMapping(value = "/guest")
    public ModelAndView guest(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","the guest can operate");
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @ResponseBody
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/permissUpdate")
    public ModelAndView permissUpdate(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg","the login has permiss user:updat  can operate");
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/testnopre")
    public String testnopre(){
        return "test testnopre";
    }

    @RequestMapping(value = "/loginout")
    public ModelAndView loginout() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");
        Subject subject= SecurityUtils.getSubject();

        try{
            if(null==subject.getPrincipal()){
                modelAndView.addObject("msg", JSONObject.toJSONString(new ResPonseResult<>(ShiroConstants.ERROR_CODE,"没有登录，无法操作!!!")));
                return modelAndView;
            }
            subject.logout();

        }catch (Exception e){
           e.printStackTrace();
        }
        modelAndView.addObject("msg", "logout sucess!!!");

        return modelAndView;
    }

    @RequestMapping(value = "/regerist")
    public ModelAndView regerist(@RequestBody Tuser tuser){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("index");

        if(StringUtils.hasText(tuser.getUserCode())&&StringUtils.hasText(tuser.getPassword())){
            tuser.setSalt(tuser.getUserCode());
            String password=new SimpleHash("MD5",tuser.getPassword(),tuser.getSalt(),10).toHex();
            log.info("regerist password:{}",password);
            tuser.setPassword(password);
            tuserService.insertOrUpdateUser(tuser);
        }
        modelAndView.addObject("msg", JSONObject.toJSONString(new ResPonseResult<>(ShiroConstants.ERROR_CODE,"regerist sucess!!!")));

        return modelAndView;
    }

}
