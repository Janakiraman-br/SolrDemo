package com.example.solrDemo;

import com.example.solrDemo.api.config.SolrConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class SolrDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolrDemoApplication.class, args);
	}

}
