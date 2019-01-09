package io.openshift.booster.insult.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class AdjectiveService {

	
	 private final RestTemplate restTemplate = new RestTemplate();
	 private final String adjectiveHost = System.getProperty("adjective.host", "http://vertx-adjective-service-devenv-vmaddali.apps.ocpmwdemo.com/");
	
 @HystrixCommand(commandKey = "AdjectiveService", fallbackMethod = "getFallbackAdjective", commandProperties = {
	      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
 })
  public String getAdjective() {
    return restTemplate.getForObject(adjectiveHost + "/api/adjective", String.class);
  }
		      
  private String getFallbackAdjective() {
	    return "unconnected non-distributed";
  }

}
