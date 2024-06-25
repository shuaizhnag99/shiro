package com.example.shiro.service.Impl;

import com.example.shiro.bean.Trole;
import com.example.shiro.mapper.TresourceMapper;
import com.example.shiro.mapper.TroleMapper;
import com.example.shiro.service.TroleService;
import com.example.shiro.vo.TroleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TroleServiceImpl implements TroleService {
    @Autowired
    private TroleMapper roleMapper;

    @Override
    public List<Trole> selectRoleByUserId(Integer userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public Trole selectRoleOne(Integer userId) {
        List<Trole> list=roleMapper.selectRoleByUserId(userId);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public TroleVo getTroleInfo(Integer userId) {
        TroleVo roleVo = new TroleVo();
        Trole role=this.selectRoleOne(userId);
        BeanUtils.copyProperties(role,roleVo);

        return roleVo;
    }
}
