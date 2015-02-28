package se.js.sample.config.server;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigServer
public class Application{
	public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

}
