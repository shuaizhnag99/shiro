package com.example.shiro.exception;

import com.alibaba.fastjson.JSONObject;
import com.example.shiro.util.ResPonseResult;
import com.example.shiro.util.ShiroConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobleExceptionAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", JSONObject.toJSONString(new ResPonseResult<>(ShiroConstants.ERROR_CODE,"您没有权限进行操作")));
        modelAndView.setViewName("error");

        log.error("GlobleExceptionAdvice UnauthorizedException {}",ex.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView noLoginExcepthandler(Exception ex){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", JSONObject.toJSONString(new ResPonseResult<>(ShiroConstants.ERROR_CODE,"请登录后操作")));
        modelAndView.setViewName("error");
        log.error("GlobleExceptionAdvice UnauthorizedException {}",ex.getMessage());

        return modelAndView;
    }
}
