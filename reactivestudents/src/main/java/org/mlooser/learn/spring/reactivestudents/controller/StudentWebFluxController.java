package org.mlooser.learn.spring.reactivestudents.controller;

import org.mlooser.learn.spring.reactivestudents.model.Student;
import org.mlooser.learn.spring.reactivestudents.repository.StudentMongoRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class StudentWebFluxController {

    private final StudentMongoRepository studentMongoRepository;

    public StudentWebFluxController(StudentMongoRepository studentMongoRepository) {
        this.studentMongoRepository = studentMongoRepository;
    }

    @GetMapping("/student/{rollNo}")
    public Mono<ResponseEntity<Student>> getStudent(@PathVariable("rollNo") Integer rollNo){
        Mono<Student> student = studentMongoRepository.findByRollNo(rollNo);
        return student.map(s -> ResponseEntity.ok(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/")
    public Flux<Student> getAll(){
        return studentMongoRepository.findAll();
    }

    @GetMapping(value = "/student-sse/", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllSSE(){
        return studentMongoRepository.findAll();
    }
}
