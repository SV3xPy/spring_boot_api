package com.example.crud_shopall;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages={
		"com.example.crud_shopall.repositories"})
@RestController

public class CrudShopallApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudShopallApplication.class, args);
	}

}