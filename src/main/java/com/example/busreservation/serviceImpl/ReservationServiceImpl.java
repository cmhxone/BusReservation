package com.example.busreservation.serviceImpl;

import com.example.busreservation.dto.Reservation;
import com.example.busreservation.event.ReservedEvent;
import com.example.busreservation.mapper.ReservationMapper;
import com.example.busreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationMapper reservationMapper;

    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public boolean reserve(String nodeid, String routeid) {

        reservationMapper.reserve(nodeid, routeid);
        
        Reservation reservation = new Reservation(nodeid, routeid);

        publisher.publishEvent(new ReservedEvent(reservation));

        return true;
    }
}
