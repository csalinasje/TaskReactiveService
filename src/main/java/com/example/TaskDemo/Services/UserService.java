package com.example.TaskDemo.Services;

import com.example.TaskDemo.Model.Usertask;
import com.example.TaskDemo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Flux<Usertask> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<Usertask> getUserById(int id){
        return userRepository.findById(id);
    }
}
