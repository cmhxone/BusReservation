package com.example.busreservation.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busreservation.authentication.detail.DriverUserDetails;
import com.example.busreservation.mapper.DriverMapper;
import com.example.busreservation.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverMapper mapper;

    @Override
    public Optional<DriverUserDetails> getDriver(String driverId, String driverName) {
        return mapper.getDriver(driverId, driverName);
    }
}
