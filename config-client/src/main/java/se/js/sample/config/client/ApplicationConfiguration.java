package se.js.sample.config.client;

import org.apache.log4j.Logger;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ApplicationConfiguration {
	private static Logger log = Logger.getLogger(ApplicationConfiguration.class);

//	@Bean
//	public ApplicationProperties applicationProperties(){
//		ApplicationProperties prop = new ApplicationProperties();
//		log.info("------------------------- call to applicationProperties: " + prop.getSomeProp2());
//		return prop;
//	}
	
}
