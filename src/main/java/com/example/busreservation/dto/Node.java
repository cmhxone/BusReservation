package com.example.busreservation.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Node {

    private String nodeid;
    private String nodeno;
    private String nodename;
    private String gpslati;
    private String gpslong;
    @Nullable
    private String headto;
}
