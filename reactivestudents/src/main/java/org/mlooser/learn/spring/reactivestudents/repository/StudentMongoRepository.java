package org.mlooser.learn.spring.reactivestudents.repository;

import org.mlooser.learn.spring.reactivestudents.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StudentMongoRepository extends ReactiveMongoRepository<Student, Integer> {
    public Mono<Student> findByRollNo(Integer rollNo);
    public Mono<Student> findByName(String name);
}
