package com.example.TaskDemo.examples;

import reactor.core.publisher.Mono;

public class MonoExample {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hola");
        mono.subscribe(
                System.out::println, //on next
                err -> System.out.println(), //on error
                ()-> System.out.println("Completed!"));// on completed

//        Mono<String> mono2 = Mono.fromSupplier(()->{ throw new RuntimeException("exception ");});
//
//        mono2.subscribe(
//                data -> System.out.println(), //on next
//                err -> System.out.println("oh no"), //on error
//                ()-> System.out.println("Completed!"));// on completed

    }
}
