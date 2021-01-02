package com.mafami.Mafami;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.mafami.Mafami.Entity.MenuEntity;
import com.mafami.Mafami.Service.MenuService;

@SpringBootApplication
@ComponentScan(basePackages = "com.mafami.Mafami")
@EnableMongoRepositories(basePackages = "com.mafami.Mafami.Repository")
@CrossOrigin
public class MafamiApplication implements CommandLineRunner{
	
	@Autowired
	private MenuService menuService;
																																																																																																																																																																																																																																																																																																																																																																																																																																																	
	public static void main(String[] args) {	
		SpringApplication.run(MafamiApplication.class, args);
	}																															

	
	@Override
	public void run(String... args) throws Exception {
		
	}
	

}
		