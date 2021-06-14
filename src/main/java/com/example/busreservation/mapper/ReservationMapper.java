package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {

    public boolean reserve(@Param("nodeid") String nodeid, @Param("routeid") String routeid);
}
