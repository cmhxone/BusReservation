package com.example.busreservation.event.handler;

import com.example.busreservation.event.ReservedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReservedEventHandler {

    @Async
    @EventListener
    public void writeLog(ReservedEvent reservedEvent) {
        log.info("Reserved (nodeid: " + reservedEvent.getReservation().getNodeid() + ", routeid: " + reservedEvent.getReservation().getRouteid());
    }
}
