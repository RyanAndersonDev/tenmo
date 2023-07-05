package com.techelevator.tenmo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.techelevator.tenmo.dao"})
public class TenmoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenmoApplication.class, args);
    }

}
