package com.example.TaskDemo.Controller;

import com.example.TaskDemo.JWT.JWTUtil;
import com.example.TaskDemo.JWT.PBKDF2Encoder;
import com.example.TaskDemo.Model.AuthRequest;
import com.example.TaskDemo.Model.AuthResponse;
import com.example.TaskDemo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
public class AuthenticationREST {
    @Autowired
    JWTUtil jwtUtil;
    @Autowired
    PBKDF2Encoder pbkdf2Encoder;
    @Autowired
    UserService userService;
    @PostMapping
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest){
        return userService.getUserByName(authRequest.getUsername())
                .filter(userDetails-> pbkdf2Encoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails-> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
