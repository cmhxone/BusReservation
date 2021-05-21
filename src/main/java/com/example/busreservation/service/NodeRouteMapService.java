package com.example.busreservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface NodeRouteMapService {

    public boolean deleteAllNodeRouteMap();
    public boolean updateNodeRouteMap(String nodeid, String routeid);
}
