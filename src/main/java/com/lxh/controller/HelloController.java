package com.lxh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘晓禾
 * @date 2019/7/2 15:12
 * @company www.tydic.com
 * @description
 */

@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
