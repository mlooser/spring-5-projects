package org.mlooser.learn.spring.reactivestudents.handler;

import org.mlooser.learn.spring.reactivestudents.model.Student;
import org.mlooser.learn.spring.reactivestudents.repository.StudentMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {
    private final StudentMongoRepository studentMongoRepository;

    public StudentHandler(StudentMongoRepository studentMongoRepository) {
        this.studentMongoRepository = studentMongoRepository;
    }

    public Mono<ServerResponse> getStudent(ServerRequest serverRequest) {
        Integer rollNo = Integer.valueOf(
                serverRequest.pathVariable("rollNo"));

        Mono<Student> student = studentMongoRepository.findByRollNo(rollNo);

        return ServerResponse
                .ok()
                .body(student, Student.class);
    }

    public Mono<ServerResponse> getAllStudent(ServerRequest serverRequest) {
        Flux<Student> allStudents = studentMongoRepository.findAll();
        return ServerResponse
                .ok()
                .body(allStudents, Student.class);
    }
}
