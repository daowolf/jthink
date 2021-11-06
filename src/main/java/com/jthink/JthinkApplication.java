/**
 * 本项目采用Apache License Version 2.0 授权协议
 * 
 * Copyright (c) 2020-2021, jthink
 */
package com.jthink;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jthink.listener.CloseListener;
import com.jthink.listener.StartListener;
import com.jthink.security.constants.JthinkSecurity;

import springfox.documentation.oas.annotations.EnableOpenApi;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({ "com.jthink.**.mapper" }) 
@EnableOpenApi
@EnableConfigurationProperties({ JthinkSecurity.class })
@EnableCaching
@EnableAsync
@EnableTransactionManagement
public class JthinkApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(JthinkApplication.class);
		application.addListeners(new StartListener());
		application.addListeners(new CloseListener());
		application.run(args);
		LoggerFactory.getLogger(JthinkApplication.class).info("《《《《《《 jthink started up successfully at {} {} 》》》》》》",
				LocalDate.now(), LocalTime.now());
	}
}
