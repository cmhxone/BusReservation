package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BusReservationMapper {

    public Map<String, Object> showTables();
}
