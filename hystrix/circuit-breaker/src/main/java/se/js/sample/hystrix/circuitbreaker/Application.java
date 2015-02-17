package se.js.sample.hystrix.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableCircuitBreaker
@RestController
public class Application {
	// -- Conxtext configuration ---
	@Bean
	public SomeService someService(){
		return new SomeService();
	}
	
	// -- REST configuration ---
	@Autowired
	private SomeService service;
	
	@RequestMapping("/")
	public String ok() {
		return service.nonFailingMethod();
	}

	@RequestMapping("/fail")
	public String fail() {
		return service.failingMethod();
	}

	@RequestMapping("/unstable")
	public String unstable() {
		return service.unstableMethod();
	}

	@RequestMapping("/slow")
	public String slow() {
		return service.slowMethod();
	}

	public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}
