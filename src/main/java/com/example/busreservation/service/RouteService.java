package com.example.busreservation.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RouteService {
	public boolean insertOrUpdateRoute(String routeid,
								  	   String routeno,
								  	   String routetp,
								  	   String startnodenm,
								  	   String endnodenm,
								  	   String startvehicletime,
								  	   String endvehicletime);

	public List<Map<String, Object>> getAllRoutes();
}
