package com.example.TaskDemo.Services;

import com.example.TaskDemo.Model.UserTask;
import com.example.TaskDemo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Flux<UserTask> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<UserTask> getUserById(int id){
        return userRepository.findById(id);
    }
}
