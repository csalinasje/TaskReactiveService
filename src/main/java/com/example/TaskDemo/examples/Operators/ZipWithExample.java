package com.example.TaskDemo.examples.Operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ZipWithExample {
    public static void main(String[] args) {
        Flux<String> flux1 = Flux.just("google","abs","fs","StackOverFlodw");
//        Flux<String> flux2 = Flux.just("pride","Monk","Walker","Mike");
        Mono<String> mono = Mono.just("pride");
        Flux<String> fluxConcat = flux1.concatWith(mono);
        fluxConcat.subscribe(element->System.out.println(element+" "));



    }
}
