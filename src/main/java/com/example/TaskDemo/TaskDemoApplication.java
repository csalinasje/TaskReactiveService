package com.example.TaskDemo;

import com.example.TaskDemo.JWT.JwtTokenAuthenticationFilter;
import com.example.TaskDemo.JWT.JwtTokenProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@SpringBootApplication
@ConfigurationPropertiesScan
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




