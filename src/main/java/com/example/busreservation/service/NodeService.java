package com.example.busreservation.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NodeService {
    public boolean insertOrUpdateNode(String nodeid, String nodeno, String nodename, String gpslati, String gpslong);
    public List<Map<String, Object>> getAllNodes();
}
