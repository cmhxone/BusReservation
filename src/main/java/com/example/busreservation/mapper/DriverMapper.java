package com.example.busreservation.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.busreservation.authentication.detail.DriverUserDetails;

@Mapper
public interface DriverMapper {
	public Optional<DriverUserDetails> getDriver(@Param(value = "driverId") String driverId,
			@Param(value = "driverName") String driverName);
}
