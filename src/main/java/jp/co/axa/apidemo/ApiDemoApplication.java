package jp.co.axa.apidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApiDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(ApiDemoApplication.class, args);
	}




}
