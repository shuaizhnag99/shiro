package com.example.shiro.controller;

import com.example.shiro.service.TuserService;
import com.example.shiro.util.ResPonseResult;
import com.example.shiro.util.ShiroConstants;
import com.example.shiro.vo.TuserVo;

import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping( "/user")
public class UserController {
    @Autowired
    TuserService tuserService;

    @RequiresPermissions("user:select")
    @GetMapping("/select")
    public ResPonseResult<TuserVo> getUserInfo(Integer userId){
       Subject subject= SecurityUtils.getSubject();
        String username=subject.getPrincipal().toString();

        log.info("getUserInfo username {} id {}",username,userId);
        if(null == userId){
            userId =1;
        }

        TuserVo troleVo = tuserService.getUserInfo(userId);
        log.info("getUserInfo userName {}",troleVo.getUserName());
        return new ResPonseResult<>(ShiroConstants.SUCCESS_CODE, ShiroConstants.SUCESS_MSG,troleVo);
    }

    @RequiresPermissions("user:select")
    @RequestMapping("/selectBycode")
    public ResPonseResult<TuserVo> getUserInfoBycode(@RequestBody  TuserVo tuserVo){
        log.info("getUserInfoBycode usercode {}",tuserVo.getUserCode());
        if(null ==tuserVo ||null == tuserVo.getUserCode()){
            return new ResPonseResult<>(ShiroConstants.FAIL_CODE,"userCode is null");
        }

        TuserVo troleVo = tuserService.getUserInfo(tuserVo.getUserCode());
        if(null != troleVo){
            log.info("getUserInfo userName {}",troleVo.getUserName());
        }

        return new ResPonseResult<>(ShiroConstants.SUCCESS_CODE, ShiroConstants.SUCESS_MSG,troleVo);
    }

}
