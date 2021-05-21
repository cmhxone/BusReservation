package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityMapper {
	
	public boolean insertOrUpdateCity(@Param("citycode")String citycode,
									  @Param("cityname") String cityname);
}
