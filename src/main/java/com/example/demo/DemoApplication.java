package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static Agent agent = new Agent();

    public static void main(String[] args) {
        agent.run();
        SpringApplication.run(DemoApplication.class, args);
    }


}
