package com.example.TaskDemo.Handler;

import com.example.TaskDemo.Exceptions.IdNotFoundException;
import com.example.TaskDemo.Model.Task;
import com.example.TaskDemo.Repositories.TaskRepository;
import com.example.TaskDemo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
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
        Mono<Task> taskSave = serverRequest.bodyToMono(Task.class)
                .flatMap(task -> taskService.saveTask(task)
                                .doOnError( e->Mono.error(new IdNotFoundException(task.getId())))
//                        .switchIfEmpty(Mono.error(new IdNotFoundException(task.getId())))
                    )
                .log();
        return ServerResponse.created(URI.create("/api/task/save")).body(taskSave,Task.class);
    }

    public Mono<ServerResponse> deleteTask(ServerRequest serverRequest) {
        int id = Integer.parseInt(serverRequest.queryParam("id").get());
        return taskService.deleteById(id)
                .switchIfEmpty(Mono.error(new IdNotFoundException(id)))
                .then(ServerResponse.ok()
                .bodyValue(id));
    }
}