package com.example.TaskDemo.examples.HotColdPublisher;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class ColdPublisherExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> netFlux = Flux.fromStream(ColdPublisherExample::getVideo)
                .delayElements(Duration.ofSeconds(2));
        netFlux.subscribe(part->System.out.println("Subscriber1: "+part));
        Thread.sleep(5000);
        netFlux.subscribe(part->System.out.println("Subscriber 2: "+ part));
        Thread.sleep(6000);
    }
    private static Stream<String> getVideo(){
        System.out.println("Request para el video");
        return Stream.of("part1","part2","part3","part4","part5");
    }
}
