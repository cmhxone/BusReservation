package com.example.busreservation.controller;

import com.example.busreservation.repository.WebSocketRepository;
import com.example.busreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("/route")
@Controller
public class RouteController {

    @Autowired
    WebSocketRepository repository;

    @Autowired
    ReservationService reservationService;

    @GetMapping("/{routeid}/{nodeid}")
    public String getRouteInfo(HttpServletRequest http,
                               @PathVariable(name = "routeid", required = true) String routeid,
                               @PathVariable(name = "nodeid", required = true) String nodeid) {

        reservationService.reserve(nodeid, routeid);

        for (WebSocketSession session : repository.getSessions()) {
            try {
                session.sendMessage(new TextMessage(routeid));
            } catch (Exception e) {
                log.error("Failed to send message on websocket session");
            }
        }

        return "redirect:/node/" + nodeid;
    }
}
