package com.kutlu.kickstatz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KickstatzApplication {

    public static void main(String[] args) {
        SpringApplication.run(KickstatzApplication.class, args);
    }

}
