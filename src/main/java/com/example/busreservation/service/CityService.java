package com.example.busreservation.service;

import org.springframework.stereotype.Service;

@Service
public interface CityService {
	
	public boolean insertOrUpdateCity(String citycode,
									  String cityname);
}
