package com.example.TaskDemo.Repositories;

import com.example.TaskDemo.JWT.AuthenticationManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository  {
    @Autowired
    AuthenticationManger authenticationManger;
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw  new UnsupportedOperationException("Not support yet");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader-> authHeader.startsWith("Bearer "))
                .flatMap(authHeader->{
                    String authToken = authHeader.substring(7);
                    Authentication authentication = new UsernamePasswordAuthenticationToken( authToken,authToken);
                    return authenticationManger.authenticate(authentication).map(SecurityContextImpl::new);
                });
    }
}