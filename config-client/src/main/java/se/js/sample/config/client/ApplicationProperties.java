package se.js.sample.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
@RefreshScope
public class ApplicationProperties{
	
	@Value("${application.service.someProp3:no value}")
	private String someProp2;
	
	public String getSomeProp2(){
		return someProp2;
	}

	
}
