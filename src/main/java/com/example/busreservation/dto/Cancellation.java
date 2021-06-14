package com.example.busreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cancellation {

    private String nodeid;
    private String routeid;
}
