package com.example.busreservation.service;

import org.springframework.stereotype.Service;

@Service
public interface NodeService {
    public boolean insertOrUpdateNode(String nodeid, String nodeno, String nodename, String gpslati, String gpslong);
}
