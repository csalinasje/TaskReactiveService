package com.example.TaskDemo.Router;

import com.example.TaskDemo.Handler.TaskHandler;
import com.example.TaskDemo.Model.Task;
import com.example.TaskDemo.Repositories.TaskRepository;
import com.example.TaskDemo.Services.TaskService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TaskRouter.class, TaskHandler.class})
@WebFluxTest
public  class TaskRouterTest {
    @Autowired
    private ApplicationContext context;
    @MockBean
    private TaskService taskService;
    @MockBean
    private TaskRepository taskRepository;
    private WebTestClient webTestClient;

    @Before
    public void setUp(){
        webTestClient = WebTestClient.bindToApplicationContext(context).build();
    }
    private Task saveTask;
    @Test
    public void saveTaskTest() throws Exception {
        Task task1= new Task(0,"laundry",true,1);
        Mockito.when(taskService.saveTask(any())).thenReturn(Mono.just(task1));
        webTestClient.post()
                .uri("/api/task/save")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(task1), Task.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(Task.class)
                .value( taskresponse->{
                    Assertions.assertEquals(taskresponse.get(0).getId(),0);
                });
    }

    @Test
    public void getTasksTest(){
        Task task1= new Task(0,"laundry",true,1);
        Task task2= new Task(1,"read",true,1);
        Mockito.when(taskService.getAllTask()).thenReturn(Flux.just(task1,task2));
        webTestClient.get()
                .uri("/api/task/tasks")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Task.class)
                .value( taskresponse->{
                    Assertions.assertEquals(taskresponse.size(),2);
                });
    }
    @Test
    public void deleteTasksTest() {
        Mono<Void> id = Mono.empty();
        Mockito.when(taskService.deleteById(anyInt())).thenReturn(id);
        webTestClient.delete()
                .uri("/api/task/delete/{id}",1)
                .exchange()
                .expectStatus().isNotFound();
    }
}