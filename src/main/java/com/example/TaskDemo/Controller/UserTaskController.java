package com.example.TaskDemo.Controller;

import com.example.TaskDemo.Model.UserTask;
import com.example.TaskDemo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class UserTaskController {
    @Autowired
    UserService userService;

    @GetMapping("/userTasks")
    public Flux<UserTask> getAllUsers(){ return listUserTask();}

    private Flux<UserTask> listUserTask(){
        return userService.getAllUsers();
    }
}
