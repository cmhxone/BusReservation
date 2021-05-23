package com.example.busreservation.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Route {

    private String routeid;
    private String routeno;
    private String routetp;
    private String startnodenm;
    private String endnodenm;
    @Nullable
    private String startvehicletime;
    @Nullable
    private String endvehicletime;
}
