package com.synex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheAssignmentApplication.class, args);
	}

}
