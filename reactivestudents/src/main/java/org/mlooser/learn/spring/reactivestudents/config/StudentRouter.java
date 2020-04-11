package org.mlooser.learn.spring.reactivestudents.config;

import org.mlooser.learn.spring.reactivestudents.handler.StudentHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class StudentRouter {

    private final StudentHandler studentHandler;

    public StudentRouter(StudentHandler studentHandler) {
        this.studentHandler = studentHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> returnStudent() {
        return RouterFunctions.route(
                RequestPredicates.GET("/api/f/student/{rollNo}"),
                studentHandler::getStudent);
    }

    @Bean
    RouterFunction<ServerResponse> returnAllStudent() {
        return RouterFunctions.route(
                RequestPredicates.GET("/api/f/student"),
                studentHandler::getAllStudent);
    }
}
