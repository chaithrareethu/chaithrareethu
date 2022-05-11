package com.core.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.core.main.mongo"})
@EnableMongoRepositories({"com.core.main.mongo"})
public class ClsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClsearchApplication.class, args);
	}

}
