package com.example.skaitest;

import com.example.skaitest.serviceImpl.TournamentService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SkaiTestApplication {

    private static final String path = "src/main/resources/static";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SkaiTestApplication.class);
        TournamentService service = context.getBean(TournamentService.class);
        service.tournamentProcessingFromFiles(path);
    }
}
