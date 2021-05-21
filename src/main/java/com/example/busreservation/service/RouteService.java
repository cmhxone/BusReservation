package com.example.busreservation.service;

import org.springframework.stereotype.Service;

@Service
public interface RouteService {
	public boolean insertOrUpdateRoute(String routeid,
								  	   String routeno,
								  	   String routetp,
								  	   String startnodenm,
								  	   String endnodenm,
								  	   String startvehicletime,
								  	   String endvehicletime);
}
