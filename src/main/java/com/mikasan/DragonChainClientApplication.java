package com.mikasan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mikasan.mapper")
public class DragonChainClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DragonChainClientApplication.class, args);
	}

}