package com.example.busreservation.websocket;

import com.example.busreservation.repository.WebSocketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class ReserveHandler extends TextWebSocketHandler {

    @Autowired
    WebSocketRepository repository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection Established. id: {}, uri: {}", session.getId(), session.getUri());

        // 웹 소켓 맵에 해당 연결정보 저장
        repository.addSession(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Message Received. id: {}, message: {}", session.getId(), message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.error("Transport Error. id: {}, error: {}", session.getId(), exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed. id: {}, status: {}", session.getId(), status.getReason());

        // 웹 소켓 맵에서 해당 연결정보 삭제
        repository.removeSession(session.getId());
    }
}
