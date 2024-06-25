package com.example.shiro.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "t_user")
public class Tuser {
    private Integer userId;
    private String userCode;
    private String userName;
    private String password;
    private String salt;
    private String status;
    private Date createTime;
    private Date updateTime;
}
