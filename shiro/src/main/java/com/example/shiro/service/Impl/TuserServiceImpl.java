package com.example.shiro.service.Impl;

import com.alibaba.fastjson2.util.DateUtils;
import com.example.shiro.bean.Tresource;
import com.example.shiro.bean.Trole;
import com.example.shiro.bean.Tuser;
import com.example.shiro.mapper.TresourceMapper;
import com.example.shiro.mapper.TroleMapper;
import com.example.shiro.mapper.TuserMapper;
import com.example.shiro.service.TuserService;
import com.example.shiro.vo.TuserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Slf4j
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

    @Override
    public void insertOrUpdateUser(Tuser tuser) {
        Tuser tuser2=tuserMapper.selectByUserCode(tuser.getUserCode());
        if(null!=tuser2){
            log.info("insertUser database has record update begin");
            tuser.setUserId(tuser2.getUserId());
            tuser.setUpdateTime(new Date());
            tuserMapper.updateById(tuser);
            return;
        }
        log.info("insertUser database has no record insert begin");

        tuser.setCreateTime(new Date());
        tuser.setUpdateTime(new Date());
        tuserMapper.insert(tuser);
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
