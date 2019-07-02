package com.lxh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 刘晓禾
 * @date 2019/7/2 15:12
 * @company www.tydic.com
 * @description
 */

@Controller
public class HelloController {


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
