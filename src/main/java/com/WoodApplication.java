package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WoodApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(WoodApplication.class, args);
		Compent compent = applicationContext.getBean("newbean", Compent.class);
		compent.show();
	}
}
