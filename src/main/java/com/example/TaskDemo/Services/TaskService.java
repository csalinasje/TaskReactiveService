package com.example.TaskDemo.Services;

import com.example.TaskDemo.Model.Task;
import com.example.TaskDemo.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    public Mono<Task> saveTask(Task task){
        return taskRepository.save(task);
    }
    public Flux<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Mono<Task> getTaskById(int id){
        return taskRepository.findById(id);
    }

    public void deleteById(int id){taskRepository.deleteById(id);}
}
