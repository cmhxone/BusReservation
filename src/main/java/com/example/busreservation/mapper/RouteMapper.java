package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RouteMapper {
	
	public boolean insertOrUpdateRoute(@Param("routeid") String routeid,
								  	   @Param("routeno") String routeno,
								  	   @Param("routetp") String routetp,
								  	   @Param("startnodenm") String startnodenm,
								  	   @Param("endnodenm") String endnodenm,
								  	   @Param("startvehicletime") String startvehicletime,
								  	   @Param("endvehicletime") String endvehicletime);

	public List<Map<String, Object>> getAllRoutes();
}
