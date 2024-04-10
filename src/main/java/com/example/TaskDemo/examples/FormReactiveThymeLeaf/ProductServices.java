package com.example.TaskDemo.examples.FormReactiveThymeLeaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;

    public Flux<Product> buscarTodo(){
        Flux<Product> flux1 = productRepository.findAll();
        Flux<Product> flux2 = productRepository.findOther();
        return Flux.merge(flux1,flux2);
    }
}
