package com.example.busreservation.service;

import com.example.busreservation.dto.Node;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface NodeService {
    public boolean insertOrUpdateNode(String nodeid, String nodeno, String nodename, String gpslati, String gpslong);
    public List<Node> getAllNodes();
}
