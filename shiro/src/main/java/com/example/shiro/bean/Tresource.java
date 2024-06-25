package com.example.shiro.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName(value = "t_resource")
public class Tresource {
    @TableId(type = IdType.AUTO)
    private Integer resourceId;
    private String resourceCode;
    private String resourceName;
    private String resourceType;
    private String resourceUrl;
    private Integer parentId;
    private Date createTime;
    private Date updateTime;
}
