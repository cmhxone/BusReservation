package com.example.busreservation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.busreservation.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/route")
@Controller
public class RouteController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/{routeid}/{nodeid}")
    public String getRouteInfo(HttpServletRequest http,
                               @PathVariable(name = "routeid", required = true) String routeid,
                               @PathVariable(name = "nodeid", required = true) String nodeid) {

        reservationService.reserve(nodeid, routeid);

        return "redirect:/node/" + nodeid;
    }
}
