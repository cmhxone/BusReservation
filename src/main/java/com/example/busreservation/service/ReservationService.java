package com.example.busreservation.service;

import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    public boolean reserve(String nodeid, String routeid);
    public boolean cancel(String nodeid, String routeid);
}
