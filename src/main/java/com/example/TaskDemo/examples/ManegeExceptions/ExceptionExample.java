package com.example.TaskDemo.examples.ManegeExceptions;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExceptionExample {
    public static void main(String[] args) {
        Flux.just(2,3,4,5,6).concatWith(Flux.error(new RuntimeException("Exception Ocurred")))
                .concatWith(Mono.just(12))
               // .onErrorReturn(74)
                .onErrorResume(err->{
                    System.out.println(err);
                    return Mono.just(12);
                })
                .log()
                .subscribe();

        Flux.just(2,3,4,5,6).concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .map(element-> {
                    if(element==4){
                        throw new RuntimeException("Exception Occurred oh no!");
                    }
                    return element;
                })
                .onErrorContinue((ex,element)->{
                    System.out.println("Exception "+ ex);
                    System.out.println("Element cause exception: "+element);
                })
                .log()
                .subscribe();

        Flux.just(2,3,4,5,6).concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .map(element-> {
                    if(element==4){
                        throw new RuntimeException("Exception Occurred on map!");
                    }
                    return element;
                })
                .onErrorMap(ex->{
                    System.out.println("Exception "+ ex);
                    return new CustomException(ex.getMessage(),ex);
                })
                .log()
                .subscribe();


    }

}
