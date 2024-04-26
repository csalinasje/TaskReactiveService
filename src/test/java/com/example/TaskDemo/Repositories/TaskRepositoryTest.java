package com.example.TaskDemo.Repositories;

import com.example.TaskDemo.Model.Task;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.postgresql.core.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    private int taskid;
    @BeforeAll
    public void insertData(){
        Task task1 = new Task();
        task1.setTitle("washing");
        task1.setCompleted(false);
        task1.setUserid(1);

        Task task2= new Task();
        task2.setTitle("laundry");
        task2.setCompleted(false);
        task2.setUserid(1);
        StepVerifier.create(taskRepository.save(task1).log())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
        StepVerifier.create(taskRepository.save(task2).log())
                .expectSubscription()
                .expectNextMatches(task-> {
                    taskid = task.getId();
                    return task.getId() != 0;
                })
                .verifyComplete();
    }
    @Test
    @Order(1)
    public void getAllTasksTest(){
        StepVerifier.create(taskRepository.findAll().log())
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();
    }
    @Test
    @Order(2)
    public void getTaskById(){
        StepVerifier.create(taskRepository.findById(taskid).log())
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }
    @Test
    @Order(3)
    public void deleteTaskById(){
        StepVerifier.create(taskRepository.deleteById(taskid).log())
                .expectSubscription()
                .verifyComplete();
    }
    @AfterAll
    public void cleanData(){
        Mono<Void> clearData = taskRepository.deleteAll();
        StepVerifier.create(clearData.log())
                .expectSubscription()
                .verifyComplete();
    }

}