package com.animal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimalApp {

    public static void main(String[] args) {
        SpringApplication.run(AnimalApp.class, args);
        System.out.println("Animal App is running...");
    }
}
