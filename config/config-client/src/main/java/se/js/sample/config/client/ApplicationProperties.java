package se.js.sample.config.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.service")
@RefreshScope
public class ApplicationProperties{

	/* This will represent the value of property "application.service.someProp3" */
	private String someProp3= "default value for someProp3";

	public String getSomeProp3() {
		return someProp3;
	}

	public void setSomeProp3(String someProp3) {
		this.someProp3 = someProp3;
	}
	
}
