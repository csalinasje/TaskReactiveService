package com.example.TaskDemo.examples;

import reactor.core.publisher.Flux;

public class FluxExample {
    public static void main(String[] args) {
        Flux<String> flux = Flux.fromArray(new String[]{"data1","data2","data3"});
        flux.subscribe(System.out::print);
        flux.doOnNext(System.out::println).subscribe();
    }
}
