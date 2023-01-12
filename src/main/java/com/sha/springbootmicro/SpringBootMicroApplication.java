package com.sha.springbootmicro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootMicroApplication {

	//./gradlew bootRun ile çalıştır

	public static void main(String[] args) {

		SpringApplication.run(SpringBootMicroApplication.class, args);




	}
}
