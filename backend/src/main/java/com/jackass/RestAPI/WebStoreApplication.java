package com.jackass.RestAPI;

import com.jackass.RestAPI.conf.ConditionsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Conditional;

@SpringBootApplication
@Conditional(ConditionsConfig.ProductionCondition.class)
public class WebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStoreApplication.class, args);
	}
}
