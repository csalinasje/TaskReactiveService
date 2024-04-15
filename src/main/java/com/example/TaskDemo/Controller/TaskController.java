package com.example.TaskDemo.Controller;

import com.example.TaskDemo.Model.Task;
import com.example.TaskDemo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping("/tasks")
//        public Flux<Task> greetingMessage(){
//        return listTask();
//        }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/save")
//        public Mono<Task> saveTask(@RequestBody Task task){
//        return  taskService.saveTask(task);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deletecontroller/{id}")
    public void deleteById (@PathVariable int id){
        taskService.deleteById(id);
    }
    private Flux<Task> listTask(){
            return taskService.getAllTask();
        }

    @GetMapping("/taskFlux")
    public Flux<Task> getFlux(){
        return taskService.getAllTask();
    }

}
