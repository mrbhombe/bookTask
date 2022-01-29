package com.task.bookTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.task.bookTask.controller","com.task.bookTask.model","com.task.bookTask.serviceImpl","com.task.bookTask.*"})
@EnableJpaRepositories({"com.task.bookTask.dao"})
public class BookTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTaskApplication.class, args);
	}

}
