package com.example.shiro.service.Impl;

import com.example.shiro.bean.Tresource;
import com.example.shiro.bean.Trole;
import com.example.shiro.bean.Tuser;
import com.example.shiro.mapper.TresourceMapper;
import com.example.shiro.mapper.TroleMapper;
import com.example.shiro.mapper.TuserMapper;
import com.example.shiro.service.TuserService;
import com.example.shiro.vo.TuserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TuserServiceImpl implements TuserService {
    @Autowired
    private TuserMapper tuserMapper;

    @Autowired
    private TroleMapper roleMapper;

    @Autowired
    private TresourceMapper tresourceMapper;
    @Override
    public TuserVo getUserInfo(Integer userId) {
        TuserVo tuserVo = new TuserVo();
        Tuser tuser=tuserMapper.selectByUserId(userId);
        if(null!=tuser){
            BeanUtils.copyProperties(tuser,tuserVo);

        }else {
            tuserVo=null;
            return tuserVo;
        }
        userAddRoleInfo(tuserVo);

        return tuserVo;
    }

    @Override
    public TuserVo getUserInfo(String userCode) {
        TuserVo tuserVo = new TuserVo();
        Tuser tuser=tuserMapper.selectByUserCode(userCode);
        if(null!=tuser){
            BeanUtils.copyProperties(tuser,tuserVo);

        }else {
            tuserVo=null;
            return tuserVo;
        }
        userAddRoleInfo(tuserVo);
        return tuserVo;
    }

    private void userAddRoleInfo(TuserVo tuserVo) {

        List<Trole> troleList=roleMapper.selectRoleByUserId(tuserVo.getUserId());
        if(!CollectionUtils.isEmpty(troleList)){
            tuserVo.setTroleList(troleList);
        }


        List<Integer> roleIdList=new ArrayList<>();
        troleList.stream().forEach(r -> {
            roleIdList.add(r.getRoleId());
        });
        List<Tresource> tresourceList=new ArrayList<>();
        if(!CollectionUtils.isEmpty(roleIdList)){
            tresourceList=tresourceMapper.findByRoleIdList(roleIdList);
        }
        if(!CollectionUtils.isEmpty(tresourceList)){
            tuserVo.setRosurceList(tresourceList);
        }
    }
}
