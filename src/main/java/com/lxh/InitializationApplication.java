package com.lxh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lxh.dao")
public class InitializationApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitializationApplication.class, args);
	}
}
