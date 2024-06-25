package com.example.shiro.vo;

import com.example.shiro.bean.Tresource;
import com.example.shiro.bean.Trole;
import lombok.Data;

import java.util.List;
@Data
public class TroleVo extends Trole {

    private List<Tresource> resources;
}
