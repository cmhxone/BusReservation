package com.example.busreservation.controller;

import com.example.busreservation.dto.Driver;
import com.example.busreservation.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DriverController {

    @Autowired
    DriverService driverService;

    @GetMapping("/show")
    @ResponseBody
    public Driver show() {
        Driver driver = driverService.getDriverById("JJB50027346").get();

        return driver;
    }
}
