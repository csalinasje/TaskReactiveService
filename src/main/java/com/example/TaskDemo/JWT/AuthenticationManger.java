package com.example.TaskDemo.JWT;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationManger implements ReactiveAuthenticationManager {
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authtoken = authentication.getCredentials().toString();
        String username = jwtUtil.getUsernameFromToken(authtoken);
        return Mono.just(jwtUtil.validateToken(authtoken))
                .filter(valid->valid)
                .switchIfEmpty(Mono.empty())
                .map(valid->{
                    Claims claims = jwtUtil.getAllClaimsFromToken(authtoken);
                    List<String> rolesMap = claims.get("role", List.class);
                    return new UsernamePasswordAuthenticationToken(username, null, rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                });
    }
}
