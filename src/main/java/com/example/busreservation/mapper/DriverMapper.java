package com.example.busreservation.mapper;

import com.example.busreservation.dto.Driver;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface DriverMapper {
    public Optional<Driver> getDriverById(@Param(value = "driverId") String driverId);
}
