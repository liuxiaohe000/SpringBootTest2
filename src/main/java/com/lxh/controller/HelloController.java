package com.lxh.controller;

import com.lxh.config.TestConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 刘晓禾
 * @date 2019/7/2 15:12
 * @company www.tydic.com
 * @description
 */

@RestController
public class HelloController {

    @Resource
    private TestConfig testConfig;

    @RequestMapping("/hello")
    public String hello(){
        return testConfig.getValue();
    }
}
