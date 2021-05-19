package com.example.busreservation.serviceImpl;

import com.example.busreservation.dto.Driver;
import com.example.busreservation.mapper.DriverMapper;
import com.example.busreservation.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverMapper mapper;

    @Override
    public Optional<Driver> getDriverById(String driverId) {
        return mapper.getDriverById(driverId);
    }
}
