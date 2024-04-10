package com.example.TaskDemo.examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class MonoFluxExample {
    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>();
        ArrayList<Integer> listaFlux = new ArrayList<>();

        Mono<Integer> mono = Mono.just(101);
        Flux<Integer> flux = Flux.just(1,2,3);

        mono.subscribe(lista::add);
        flux.subscribe(listaFlux::add);

        System.out.println(lista);
        System.out.println(listaFlux);
    }
}
