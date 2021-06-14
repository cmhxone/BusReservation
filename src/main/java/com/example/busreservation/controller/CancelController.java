package com.example.busreservation.controller;

import com.example.busreservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/cancel")
@Controller
public class CancelController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/{routeid}/{nodeid}")
    public String cancel(@PathVariable(name = "routeid") String routeid,
                         @PathVariable(name = "nodeid") String nodeid) {

        reservationService.cancel(nodeid, routeid);

        return "redirect:/node/" + nodeid;
    }
}
