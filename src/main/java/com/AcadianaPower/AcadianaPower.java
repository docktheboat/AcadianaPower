package com.AcadianaPower;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableEncryptableProperties
@EnableScheduling
public class AcadianaPower {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(AcadianaPower.class, args);
	}

}
@RestController
class Welcome{


	@GetMapping("/")
	public String welcome(){
		return "Welcome To Acadiana Power";
	}
}
