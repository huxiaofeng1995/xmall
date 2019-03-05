package com.hxf.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hxf.mall.mapper")
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
