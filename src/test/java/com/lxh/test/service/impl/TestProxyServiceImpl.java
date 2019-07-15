package com.lxh.test.service.impl;

import com.lxh.test.service.TestProxyService;

/**
 * @author 刘晓禾
 * @date 2019/7/15 11:08
 * @company www.tydic.com
 * @description
 */
public class TestProxyServiceImpl implements TestProxyService{

    @Override
    public void addtest(String s) {
        System.out.println("测试动态代理"+s);
    }

    @Override
    public String toString() {
        return "testProxyService:111";
    }
}
