package com.example.TaskDemo.examples.Operators;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FlatMap {
    public static void main(String[] args) {
        Flux.fromArray(new String[]{"Tom", "Melisa","Steve","Megan"})
                .flatMap(FlatMap::ponerNombreModificadoEnMono)
                .subscribe(System.out::println);

    }

    private static Mono<String> ponerNombreModificadoEnMono(String nombre){
        return Mono.just(nombre.concat("modificado"));
    }
}
