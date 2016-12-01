package es.uc3m.tiw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class GatewayApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
