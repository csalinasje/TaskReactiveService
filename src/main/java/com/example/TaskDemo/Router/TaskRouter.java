package com.example.TaskDemo.Router;

import com.example.TaskDemo.Handler.TaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TaskRouter {
    @Bean
    public RouterFunction<ServerResponse> functionalTaskRoutes(TaskHandler taskHandler){
        return RouterFunctions
                .route(RequestPredicates.GET("/api/task/tasks"),taskHandler::getAllTasks)
                .andRoute(RequestPredicates.POST("/api/task/save"),taskHandler::saveTask)
                .andRoute(RequestPredicates.DELETE("/api/task/delete"),taskHandler::deleteTask);
    }
}
