package com.tutorial.psqlpsqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
public class PsqlPsqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsqlPsqlDemoApplication.class, args);
	}
}
