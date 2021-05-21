package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NodeMapper {

    public boolean insertOrUpdateNode(@Param("nodeid") String nodeid,
                                      @Param("nodeno") String nodeno,
                                      @Param("nodename") String nodename,
                                      @Param("gpslati") String gpslati,
                                      @Param("gpslong") String gpslong);

    public List<Map<String, Object>> getAllNodes();
}
