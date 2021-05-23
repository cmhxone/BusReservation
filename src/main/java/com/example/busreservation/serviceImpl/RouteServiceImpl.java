package com.example.busreservation.serviceImpl;

import com.example.busreservation.dto.Route;
import com.example.busreservation.mapper.RouteMapper;
import com.example.busreservation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	private RouteMapper mapper;

	@Override
	public boolean insertOrUpdateRoute(String routeid,
									   String routeno,
									   String routetp,
									   String startnodenm,
									   String endnodenm,
									   String startvehicletime,
									   String endvehicletime) {
		return mapper.insertOrUpdateRoute(routeid, routeno, routetp, startnodenm, endnodenm, startvehicletime, endvehicletime);
	}

	@Override
	public List<Route> getAllRoutes() {
		return mapper.getAllRoutes();
	}

}
