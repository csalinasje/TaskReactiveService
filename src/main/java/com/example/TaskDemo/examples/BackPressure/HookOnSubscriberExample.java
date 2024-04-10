package com.example.TaskDemo.examples.BackPressure;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class HookOnSubscriberExample {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1,100).log();
        flux.subscribe(new BaseSubscriber<>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(5);
            }

        });
    }
}
