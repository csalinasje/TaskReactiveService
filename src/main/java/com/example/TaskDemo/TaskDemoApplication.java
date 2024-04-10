package com.example.TaskDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class TaskDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskDemoApplication.class, args);
	}
	@Bean
	public CorsFilter corstFilter(){
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200","**"));
		corsConfig.setAllowedHeaders(Arrays.asList("*","Origin","Access-Control-Allow-Origin","Content-type",
		"Accept","Authorization","Origin, Accept","X-Requested-With","Access-Control-Request-Method",
				"Acces-Control-Request-Headers"));
		corsConfig.setExposedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","*","Content-type",
				"Accept","Authorization","Access-Control-Allow-Credentials"));
		corsConfig.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}




