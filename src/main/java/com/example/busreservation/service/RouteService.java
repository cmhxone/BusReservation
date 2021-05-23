package com.example.busreservation.service;

import com.example.busreservation.dto.Route;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {
	public boolean insertOrUpdateRoute(String routeid,
								  	   String routeno,
								  	   String routetp,
								  	   String startnodenm,
								  	   String endnodenm,
								  	   String startvehicletime,
								  	   String endvehicletime);

	public List<Route> getAllRoutes();
}
