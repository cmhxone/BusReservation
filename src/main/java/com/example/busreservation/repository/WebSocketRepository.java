package com.example.busreservation.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 연결된 웹 소켓을 저장하는 저장소
 *
 * @author jomuhyeon
 * @date 2021-06-07
 */
@Slf4j
@Component
public class WebSocketRepository {

    private HashMap<String, WebSocketSession> webSocketSessionHashMap = new HashMap<>();

    /**
     * 세션을 ID를 통해 조회 후 반환하는 메소드
     *
     * @param sessionID 세션ID
     * @return 연결된 세션
     */
    public WebSocketSession getSession(String sessionID) {
        return webSocketSessionHashMap.get(sessionID);
    }

    /**
     * 세션을 저장소에 추가하는 함수
     *
     * @param sessionID 세션ID
     * @param sesion    세션
     */
    public void addSession(String sessionID, WebSocketSession sesion) {
        webSocketSessionHashMap.put(sessionID, sesion);
    }

    /**
     * 세션을 저장소에서 제거하는 함수
     *
     * @param sessionID 세션ID
     */
    public void removeSession(String sessionID) {
        webSocketSessionHashMap.remove(sessionID);
    }

    /**
     * 모든 세션을 반환하는 함수
     *
     * @return 저장소에 저장된 모든 세션
     */
    public ArrayList<WebSocketSession> getSessions() {
        ArrayList<WebSocketSession> sessions = new ArrayList<>();

        for (WebSocketSession session : webSocketSessionHashMap.values()) {
            sessions.add(session);
        }

        return sessions;
    }
}
