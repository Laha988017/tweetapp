package com.tweetapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TweetappApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetappApplication.class, args);
	}

}
