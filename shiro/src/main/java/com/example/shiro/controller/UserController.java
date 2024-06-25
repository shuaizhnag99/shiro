package com.example.shiro.controller;

import com.example.shiro.service.TuserService;
import com.example.shiro.vo.TuserVo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping( "/user")
public class UserController {
    @Autowired
    TuserService tuserService;

    @RequestMapping("/select")
    public TuserVo getUserInfo(Integer userId){
        log.info("getUserInfo id {}",userId);
        if(null == userId){
            userId =1;
        }

        TuserVo troleVo = tuserService.getUserInfo(userId);

        log.info("getUserInfo userName {}",troleVo.getUserName());
        return troleVo;
    }

}
