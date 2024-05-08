package com.example.TaskDemo.Repositories;

import com.example.TaskDemo.Model.UserTask;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserTask, Integer> {
    Mono<UserTask> findByName(String name);
}
