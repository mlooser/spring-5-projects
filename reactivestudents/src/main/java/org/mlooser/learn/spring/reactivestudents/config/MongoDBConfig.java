package org.mlooser.learn.spring.reactivestudents.config;

import lombok.extern.java.Log;
import org.mlooser.learn.spring.reactivestudents.model.Student;
import org.mlooser.learn.spring.reactivestudents.repository.StudentMongoRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Log
public class MongoDBConfig {

    @Bean
    public ApplicationRunner dbInitializer(StudentMongoRepository studentMongoRepository){
        return args -> {
            log.info("Init MongoDB data");
            List<Student> students = new ArrayList<>();
            for(int i=0;i<10;++i){
                String name = Character.toString ((char)(65 + i) );
                students.add( new Student(Integer.toString(i),i, name, 1 ));
            }
            Student s1 = new Student("1",1,"AA",1);
            studentMongoRepository.saveAll(students).blockLast();
        };
    }
}
