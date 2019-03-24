package dev.erictu.security.eyesecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()
@RestController
public class EyeSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EyeSecurityDemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello spring security";
	}
}
