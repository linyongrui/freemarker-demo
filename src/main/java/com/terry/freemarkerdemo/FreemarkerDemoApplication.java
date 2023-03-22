package com.terry.freemarkerdemo;

import com.terry.freemarkerdemo.service.GenerateWordService1;
import com.terry.freemarkerdemo.service.GetResourceFileTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FreemarkerDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FreemarkerDemoApplication.class, args);
		GetResourceFileTest getResourceFileTest = context.getBean(GetResourceFileTest.class);
		getResourceFileTest.getResouceFile();
//		GenerateWordService1 generateWordService1 = context.getBean(GenerateWordService1.class);
//		generateWordService1.getResouceFile();
	}

}
