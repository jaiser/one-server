/**
 * Copyright (C), 2020, 中电福富信息科技有限公司
 * FileName: TestController
 * Author:   xujiajun
 * Date:     2020/5/14 0:56
 */
package com.jaiser.oneserver.web.controller;

import com.jaiser.oneserver.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 
 * @Author xujiajun
 * @Date 2020/5/14  0:56
 */
@RestController
@RequestMapping(value = "/test")
@Api("test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation("测试接口")
    @PostMapping("/hello")
    public String hello() {
        logger.info("test hello");
        String token = JwtUtil.createToken("12345", null);
        return token ;
    }

    @ApiOperation("解密token")
    @PostMapping("/verifyToken")
    public void verifyToken(@RequestBody String token) {
        logger.info("verifyToken token" + token);
        JwtUtil.verifyToken(token);

    }
}