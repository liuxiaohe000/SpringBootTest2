package com.lxh.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 刘晓禾
 * @date 2019/7/2 17:14
 * @company www.tydic.com
 * @description 用来测试读取
 *
 * * 1、@ConfigurationProperties 告诉SpringBoot将配置文件中对应属性的值，映射到这个组件类中,进
 *   行一一绑定
 *   prefix = "spring.test"：配置文件中的前缀名，哪个前缀与下面的所有属性进行一一映射
 *  2、@Component 必须将当前组件作为SpringBoot中的一个组件，才能使用容器提供的
 *
 *
 */

@Service
@ConfigurationProperties(prefix = "spring.test")
public class TestConfig {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TestConfig{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
