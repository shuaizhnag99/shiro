package com.example.shiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.shiro.bean.Tuser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TuserMapper extends BaseMapper<Tuser> {

    Tuser selectByUserId(@Param("userId")Integer userId);

    Tuser selectByUserCode(@Param("userCode")String userCode);


}
