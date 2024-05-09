package com.example.TaskDemo.Controller;

import com.example.TaskDemo.Model.Usertask;
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
    public Flux<Usertask> getAllUsers(){ return listUserTask();}

    private Flux<Usertask> listUserTask(){
        return userService.getAllUsers();
    }
}
