package com.example.TaskDemo.examples.Operators;

import reactor.core.publisher.Flux;

public class FilterExample {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Tom", "Melisa","Steve","Megan"})
                .filter(name->name.length() >5)
                .map(String::toUpperCase)
                .subscribe(System.out::println);

    }
}
