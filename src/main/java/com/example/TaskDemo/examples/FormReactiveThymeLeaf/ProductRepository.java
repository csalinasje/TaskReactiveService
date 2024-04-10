package com.example.TaskDemo.examples.FormReactiveThymeLeaf;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static List<Product> list = new ArrayList<>();
    private static List<Product> list2 = new ArrayList<>();
    static {
        list.add(new Product(1,"laptop",200));
        list.add(new Product(2,"headset",300));
        list.add(new Product(3,"mouse",400));
        list.add(new Product(4,"screen",130));
        list.add(new Product(5,"mobile",400));

        list2.add(new Product(1,"keyboard",200));
        list2.add(new Product(2,"cable",300));
        list2.add(new Product(3,"mousetap",400));

    }
    public Flux<Product> findAll(){
        return Flux.fromIterable(list).delayElements(Duration.ofSeconds(3));
    }
    public Flux<Product> findOther(){ return Flux.fromIterable(list2).delayElements(Duration.ofSeconds(3));}
}
