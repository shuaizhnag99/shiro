package com.example.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro.bean.Trole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TroleMapper extends BaseMapper<Trole> {

    List<Trole>  selectRoleByUserId(@Param("userId") Integer userId);

}
