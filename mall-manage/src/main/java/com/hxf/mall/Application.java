package com.hxf.mall;

import com.hxf.mall.test.CustomMultipartResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
@MapperScan("com.hxf.mall.mapper")
public class Application{

	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver()
	{
		return new CustomMultipartResolver();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
