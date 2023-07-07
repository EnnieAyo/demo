package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.FEBRUARY;
import static java.time.Month.JUNE;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student ennie = new Student(
                    "Ennie",
                    "ennie@waziri.com",
                    LocalDate.of(1993, JUNE, 16)
            );

            Student ammy = new Student(
                    "Ammy",
                    "ammy@waziri.com",
                    LocalDate.of(1997, FEBRUARY, 6)
            );

            repository.saveAll(
                    List.of(ennie, ammy)
            );
        };
    }
}
