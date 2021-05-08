package com.example.busreservation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
public class BusReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusReservationApplication.class, args);
    }

}
