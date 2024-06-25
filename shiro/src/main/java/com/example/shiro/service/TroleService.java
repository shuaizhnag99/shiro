package com.example.shiro.service;

import com.example.shiro.bean.Trole;
import com.example.shiro.vo.TroleVo;

import java.util.List;

public interface TroleService {
    List<Trole> selectRoleByUserId(Integer userId);

    Trole selectRoleOne(Integer userId);

    TroleVo getTroleInfo(Integer userId);
}
