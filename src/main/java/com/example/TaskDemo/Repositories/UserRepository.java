package com.example.TaskDemo.Repositories;

import com.example.TaskDemo.Model.Usertask;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Usertask, Integer> {
    public Mono<Usertask> findByUsername(String username);
}
