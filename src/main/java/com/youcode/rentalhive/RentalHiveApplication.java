package com.youcode.rentalhive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RentalHiveApplication {

    public static void main(String[] args) {

        SpringApplication.run(RentalHiveApplication.class, args);
    }

}
