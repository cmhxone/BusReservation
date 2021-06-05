package com.example.busreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableWebSocket
@EnableAsync
@EnableScheduling
@PropertySource({"classpath:/properties/datasource.yml", "classpath:/properties/datagokr.yml"})
@SpringBootApplication()
public class BusReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusReservationApplication.class, args);
    }

}
