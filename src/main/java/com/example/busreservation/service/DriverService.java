package com.example.busreservation.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.busreservation.authentication.detail.DriverUserDetails;

@Service
public interface DriverService {
    public Optional<DriverUserDetails> getDriver(String driverId, String driverName);
}
