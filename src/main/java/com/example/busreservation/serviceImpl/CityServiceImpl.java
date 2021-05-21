package com.example.busreservation.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.busreservation.mapper.CityMapper;
import com.example.busreservation.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityMapper mapper;
	
	@Override
	public boolean insertOrUpdateCity(String citycode,
									  String cityname) {
		return mapper.insertOrUpdateCity(citycode, cityname);
	}

}
