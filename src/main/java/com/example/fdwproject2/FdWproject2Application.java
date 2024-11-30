package com.example.fdwproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket //ups
public class FdWproject2Application {

    public static void main(String[] args) {
        SpringApplication.run(FdWproject2Application.class, args);
    }

}
