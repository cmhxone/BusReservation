package com.example.busreservation.event;

import com.example.busreservation.dto.Cancellation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelledEvent {

    private Cancellation cancellation;
}
