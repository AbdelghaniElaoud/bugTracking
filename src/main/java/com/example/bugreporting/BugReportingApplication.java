package com.example.bugreporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class BugReportingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugReportingApplication.class, args);
    }

}
