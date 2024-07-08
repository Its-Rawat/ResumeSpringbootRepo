package com.rawat.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoManagerApplication.class, args);
		System.out.println("Hey this is Me!!");
	}

}
