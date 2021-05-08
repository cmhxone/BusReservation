package com.example.busreservation.service;

import com.example.busreservation.mapper.BusReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BusReservationServiceImpl implements BusReservationService {

    @Autowired
    BusReservationMapper mapper;

    @Override
    public Map<String, String> showTables() {
        return mapper.showTables();
    }
}
