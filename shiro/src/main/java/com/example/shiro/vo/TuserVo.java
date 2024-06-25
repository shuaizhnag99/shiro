package com.example.shiro.vo;

import com.example.shiro.bean.Tresource;
import com.example.shiro.bean.Trole;
import com.example.shiro.bean.Tuser;
import lombok.Data;

import java.util.List;

@Data
public class TuserVo extends Tuser {

    private List<Trole> troleList;

    private List<Tresource> rosurceList;

}
