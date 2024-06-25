package com.example.shiro.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "t_userRole")
public class TuserRole {
    private Integer userRoleId;
    private Integer userId;
    private Integer roleId;
    private String status;
    private Date createTime;
    private Date updateTime;
}
