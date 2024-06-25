package com.example.shiro.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "t_role_resource")
public class TrolerRsource {
    private Integer roleResourceId;
    private Integer resourceId;
    private Integer roleId;
    private String status;
    private Date createTime;
    private Date updateTime;
}
