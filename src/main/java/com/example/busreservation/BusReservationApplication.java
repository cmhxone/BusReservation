package com.example.busreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource({"classpath:/properties/datasource.yml"})
@SpringBootApplication()
public class BusReservationApplication {

    public static void main(String[] args) { SpringApplication.run(BusReservationApplication.class, args); }

}
