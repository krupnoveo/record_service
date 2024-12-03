package ru.krupnoveo.edu.record_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecordServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordServiceApplication.class, args);
    }

}
