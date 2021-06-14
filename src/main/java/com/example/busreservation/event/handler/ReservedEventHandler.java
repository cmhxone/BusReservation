package com.example.busreservation.event.handler;

import com.example.busreservation.event.ReservedEvent;
import com.example.busreservation.repository.ReservationRepository;
import com.example.busreservation.repository.WebSocketRepository;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@Component
public class ReservedEventHandler {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private WebSocketRepository websocketRepository;

	@Async
	@EventListener
	public void writeLog(ReservedEvent reservedEvent) {
		reservationRepository.addReservation(reservedEvent.getReservation().getRouteid().substring(7, 10));

		log.info(reservationRepository.getReservation().toString());

		for (WebSocketSession session : websocketRepository.getSessions()) {
			try {
				session.sendMessage(new TextMessage(
						reservationRepository.getReservation().toString().replace("[", "").replace("]", "")));
			} catch (IOException e) {
				log.error("Failed to send reservation on websocket.");
			}
		}

		log.info("Reserved (nodeid: " + reservedEvent.getReservation().getNodeid() + ", routeid: "
				+ reservedEvent.getReservation().getRouteid());
	}
}
