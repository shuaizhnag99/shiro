package com.example.shiro;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.util.DateUtils;
import com.example.shiro.controller.UserController;
import com.example.shiro.service.TuserService;
import com.example.shiro.vo.TuserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
@SpringBootTest
class ShiroApplicationTests {
	@Autowired
	TuserService tuserService;


	@Test
	void contextLoads() {
		TuserVo tuserVo= tuserService.getUserInfo(1);
		 String d1= DateUtils.format(2024,4,1);
		String d3= DateUtils.format(2024,6,1);
		String d2= DateUtils.format(2024,6,21);


		log.info("day 1 lingTime {}",DateUtils.parseDate(d1).getTime());
		log.info("day 2 lingTime {}",DateUtils.parseDate(d2).getTime());
		log.info("day 3 lingTime {}",DateUtils.parseDate(d3).getTime());


	}

}
