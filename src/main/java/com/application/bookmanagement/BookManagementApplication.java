package com.application.bookmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BookManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookManagementApplication.class, args);
	}

}
