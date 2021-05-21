package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NodeRouteMapMapper {

    public boolean deleteAllNodeRouteMap();
    public boolean updateNodeRouteMap(@Param("nodeid") String nodeid, @Param("routeid") String routeid);
}
