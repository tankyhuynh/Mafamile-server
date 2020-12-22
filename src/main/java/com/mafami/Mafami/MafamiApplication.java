package com.mafami.Mafami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = "com.mafami.Mafami")
@EnableMongoRepositories(basePackages = "com.mafami.Mafami.Repository")
@CrossOrigin
public class MafamiApplication{
																																																																																																																																																																																																																																																																																																																																																																																																																																																	
	public static void main(String[] args) {	
		SpringApplication.run(MafamiApplication.class, args);
	}																															

	
	
	

}
		