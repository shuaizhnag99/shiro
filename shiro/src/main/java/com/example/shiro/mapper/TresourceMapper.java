package com.example.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro.bean.Tresource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TresourceMapper extends BaseMapper<Tresource> {

    List<Tresource> findByUserId(@Param("userId") Integer userId);

    List<Tresource> findByRoleId(@Param("roleId") Integer roleId);

    List<Tresource> findByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

}
