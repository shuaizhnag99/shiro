package com.example.shiro.service;

import com.example.shiro.vo.TuserVo;

public interface TuserService {
    TuserVo getUserInfo(Integer userId);

    TuserVo getUserInfo(String userCode);
}
