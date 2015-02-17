package se.js.sample.hystrix.circuitbreaker;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class SomeService {
	private Log log = LogFactory.getLog(SomeService.class);
	
	private Random rnd = new Random();
	
	@HystrixCommand(fallbackMethod = "fallback")
	public String failingMethod() {
		log.info("failingMethod: Service is failing!!");
		throw new RuntimeException("fail now");			
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public String nonFailingMethod() {
		log.info("nonFailingMethod: Service OK");
		return "OK";
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public String unstableMethod() {
		if(rnd.nextBoolean()){
			log.info("unstableMethod: Service is failing!!");
			throw new RuntimeException("fail now");			
		}
		log.info("unstableMethod: Service OK");
		return "OK";
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public String slowMethod() {
		int sleep = rnd.nextInt(1000);
		log.info("slowMethod: Service is delayed " + sleep);
		try {
			Thread.sleep(sleep);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		return "OK";
	}

	public String fallback() {
		log.info("Service is provided from FALLBACK");
		return "from the fallback";
	}

}
