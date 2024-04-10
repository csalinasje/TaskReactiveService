package com.example.TaskDemo.examples.Operators;

import reactor.core.publisher.Flux;

public class MapExample {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Tom", "Melisa","Steve","Megan"})
                .map(String::toUpperCase)
                .subscribe(System.out::println);

    }
}
