package com.example.busreservation.controller;

import com.example.busreservation.service.BusReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BusReservationController {

    @Autowired
    BusReservationService service;

    @GetMapping("/dbtest")
    @ResponseBody
    public Object databaseTest() {
        Object result = null;
        result = service.showTables();

        return result;
    }

}
