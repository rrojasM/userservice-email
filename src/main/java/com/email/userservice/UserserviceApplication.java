package com.email.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication(scanBasePackages = {"controller","service","model", "repository"})
@SpringBootApplication
@EnableAsync
@EnableWebMvc
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
}
