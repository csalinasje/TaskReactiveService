package com.example.TaskDemo.Handler;

import com.example.TaskDemo.Model.Task;
import com.example.TaskDemo.Repositories.TaskRepository;
import com.example.TaskDemo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class TaskHandler {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskRepository repository;

    public Mono<ServerResponse> getAllTasks(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskService.getAllTask(), Flux.class)
                .log();
    }

    public Mono<ServerResponse> saveTask(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Task.class)
                .flatMap(task -> taskService.saveTask(task))
                .flatMap(task -> ServerResponse.created(URI.create("/api/task/save")).build());
    }

    public Mono<ServerResponse> deleteTask(ServerRequest serverRequest) {
        return Mono.just(taskService.deleteById(Integer.parseInt(serverRequest.pathVariable("id"))))
                .flatMap(val->{
                    return ServerResponse.noContent().build();
                });
    }
}