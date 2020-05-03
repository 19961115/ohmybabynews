package com.uestc.ohmynews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OhmynewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OhmynewsApplication.class, args);
    }

}
