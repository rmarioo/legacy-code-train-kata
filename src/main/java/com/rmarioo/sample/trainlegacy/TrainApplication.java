package com.rmarioo.sample.trainlegacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainApplication {

	public static Boolean fromTestEnvironment = true;

	public static void main(String[] args) {

		fromTestEnvironment = false;
		SpringApplication.run(TrainApplication.class, args);
	}

}
