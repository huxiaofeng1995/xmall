package com.hxf.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.hxf.mall.mapper")
@ComponentScan(basePackages = "com.hxf.mall")
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
