package com.practice2.SpringPractice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringPractice2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringPractice2Application.class, args);
	}


}
@RestController
class Welcome{
	@GetMapping("/")
	public String welcome(){
		return "Welcome";
	}
}
