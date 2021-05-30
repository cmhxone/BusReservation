package com.example.busreservation.event;

import com.example.busreservation.dto.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservedEvent {

    private Reservation reservation;
}
