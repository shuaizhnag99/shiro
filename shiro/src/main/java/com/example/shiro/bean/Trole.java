package com.example.shiro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "t_role")
public class Trole {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    private String roleCode;
    private String roleName;
    private String status;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;

}
