package com.terry.freemarkerdemo;

import com.terry.freemarkerdemo.service.GenerateWordService1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FreemarkerDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FreemarkerDemoApplication.class, args);
		GenerateWordService1 generateWordService1 = context.getBean(GenerateWordService1.class);
		generateWordService1.getResouceFile();
	}

}
