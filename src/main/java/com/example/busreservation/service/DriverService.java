package com.example.busreservation.service;

import com.example.busreservation.dto.Driver;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DriverService {
    public Optional<Driver> getDriverById(String driverId);
}
