package com.example.TaskDemo.examples.WebFlux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class HelloHandler {
    public Mono<ServerResponse> mostrarMensajeMono(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Welcome to my channel task"),String.class)
                .log();
    }
    public Mono<ServerResponse> mostrarFlux(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Flux.just("Welcome to my channel task"),String.class)
                .log();
    }
}
