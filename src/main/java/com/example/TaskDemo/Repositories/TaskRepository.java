package com.example.TaskDemo.Repositories;

import com.example.TaskDemo.Model.Task;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, Integer> {
}
