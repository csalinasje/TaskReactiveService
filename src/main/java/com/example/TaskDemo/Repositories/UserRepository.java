package com.example.TaskDemo.Repositories;

import com.example.TaskDemo.Model.UserTask;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserTask, String> {
}
