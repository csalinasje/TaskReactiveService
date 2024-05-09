package com.example.TaskDemo.Services;

import com.example.TaskDemo.Model.Role;
import com.example.TaskDemo.Model.UserTask;
import com.example.TaskDemo.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {
    private Map<String, UserTask> data;
    @Autowired
    private UserRepository userRepository;
    @PostConstruct
    public void init(){
        data = new HashMap<>();
        // username:password -> user:user
        data.put("user", new UserTask(1,"user",null,"cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", List.of(Role.ROLE_USER)));
        //username:password -> admin:admin
        data.put("admin", new UserTask(1,"admin",null, "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", List.of(Role.ROLE_ADMIN)));

    }
    public Flux<UserTask> getAllUsers(){
        return userRepository.findAll();
    }

    public Mono<UserTask> getUserById(int id){
        return userRepository.findById(id);
    }

    public Mono<UserTask> getUserByName(String name){ return Mono.justOrEmpty(data.get(name));}
}
