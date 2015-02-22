package se.js.sample.config.client;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@EnableAutoConfiguration
@RestController
public class Application implements ApplicationListener<ApplicationEvent>{
	private static Logger log = Logger.getLogger(Application.class);

	@Value("${prop1:no value}")
	String prop1;
	
	@Value("${prop2:no value}")
	String prop2;
	
	@Value("${application.service.someProp:no value}")
	String someProp;

	@Autowired
	ApplicationProperties props;

	// -- REST configuration ---
	@RequestMapping("/")
	public String home() {
		return prop1 + " :: " + prop2 + " :: " + someProp + " :: " + props.getSomeProp2();
	}

	public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
//        new SpringApplicationBuilder(Application.class).run(args);
    }

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		log.info("-- onApplicationEvent:: " + event);
		log.info("-- prop1: " + prop1);
		log.info("-- prop2: " + prop2);
		log.info("-- someProp: " + someProp);
		log.info("-- ApplicationProperties.someProp2: " + props.getSomeProp2());
	}
}
