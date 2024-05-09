package com.example.TaskDemo.JWT;

import com.example.TaskDemo.Repositories.SecurityContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
    @Autowired
    AuthenticationManger authenticationManger;
    @Autowired
    SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity httpSecurity){
        return  httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint((swe, e)->
                        Mono.fromRunnable(()->swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
                .accessDeniedHandler((swe,e)->
                        Mono.fromRunnable(()->swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
                .and()
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .formLogin(formLoginSpec -> formLoginSpec.loginPage("/login"))
                .httpBasic().disable()
                .authenticationManager(authenticationManger)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and().build();
//        httpSecurity
//                .authorizeExchange(exchanges -> exchanges
//                        .anyExchange().authenticated()
//                )
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .httpBasic(withDefaults())
//                .authenticationManager(authenticationManger)
//                .securityContextRepository(securityContextRepository)
//                .formLogin(withDefaults());
//        return         httpSecurity
//                .build();
    }
}
