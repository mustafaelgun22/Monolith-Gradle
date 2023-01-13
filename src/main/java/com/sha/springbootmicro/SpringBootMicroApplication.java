package com.sha.springbootmicro;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableScheduling
public class SpringBootMicroApplication {

	//./gradlew bootRun ile çalıştır

	public static void main(String[] args) {

		SpringApplication.run(SpringBootMicroApplication.class, args);




	}
}
