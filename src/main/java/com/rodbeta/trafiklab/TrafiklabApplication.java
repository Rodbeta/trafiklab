package com.rodbeta.trafiklab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class }) //, scanBasePackages = "com.rodbeta.trafiklab.resources")
public class TrafiklabApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrafiklabApplication.class, args);
	}

}
