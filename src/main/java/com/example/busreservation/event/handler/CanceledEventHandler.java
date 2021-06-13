package com.example.busreservation.event.handler;

import com.example.busreservation.event.CancelledEvent;
import com.example.busreservation.repository.ReservationRepository;
import com.example.busreservation.repository.WebSocketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Slf4j
@Component
public class CanceledEventHandler {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private WebSocketRepository webSocketRepository;

    @Async
    @EventListener
    public void cancelReservation(CancelledEvent cancelledEvent) {
        reservationRepository.removeReservation(cancelledEvent.getCancellation().getRouteid().substring(7, 10));

        log.info(reservationRepository.getReservation().toString());

        for (WebSocketSession session : webSocketRepository.getSessions()) {
            try {
                session.sendMessage(new TextMessage(
                        reservationRepository.getReservation().toString().replace("[", "").replace("]", "")));
            } catch (IOException e) {
                log.error("Failed to send cancellation on websocket.");
            }
        }

        log.info("Cancellation event called nodeid: " + cancelledEvent.getCancellation().getNodeid() + ", routeid: " + cancelledEvent.getCancellation().getRouteid());

    }
}
