package se.js.sample.hystrix.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Hystrix.
 *
 * @author Lieven Doclo
 */
@Configuration
@EnableConfigurationProperties(HystrixProperties.class)
@ConditionalOnProperty(prefix="hystrix", name="enabled", matchIfMissing=true)
class HystrixConfiguration {
    @Autowired
    HystrixProperties hystrixProperties;

    @Bean
    @ConditionalOnClass(HystrixCommandAspect.class)
    HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }
    
    @Bean
    @ConditionalOnClass(HystrixMetricsStreamServlet.class)
    @ConditionalOnProperty(prefix="hystrix", name="streamEnabled", matchIfMissing=false)
    public ServletRegistrationBean hystrixStreamServlet(){
    	return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), hystrixProperties.streamUrl);
    }
}
/**
 * Configuration properties for Hystrix.
 *
 * @author Lieven Doclo
 */
@ConfigurationProperties(prefix = "hystrix", ignoreUnknownFields = true)
class HystrixProperties {
    boolean enabled = true;
    boolean streamEnabled = false;
    String streamUrl = "/hystrix.stream";
}
