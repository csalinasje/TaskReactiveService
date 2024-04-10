package com.example.TaskDemo.examples.BackPressure;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class BackPressure {
    public static void main(String[] args) {
        Flux<String> ciudades = Flux.fromIterable(new ArrayList<>(Arrays.asList("New York", "London", "Paris", "Toronto")));
        ciudades.log().subscribe();
    }

}
