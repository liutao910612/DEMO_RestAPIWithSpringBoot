package com.liutao.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 用于配置和启动引导
 */
//开启了组件扫描和自动配置功能
@SpringBootApplication
@ComponentScan(basePackages={"com.liutao"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
