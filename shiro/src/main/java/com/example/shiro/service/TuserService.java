package com.example.shiro.service;

import com.example.shiro.bean.Tuser;
import com.example.shiro.vo.TuserVo;

public interface TuserService {
    TuserVo getUserInfo(Integer userId);

    TuserVo getUserInfo(String userCode);

    void insertOrUpdateUser(Tuser tuser);
}
