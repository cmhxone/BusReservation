package com.example.busreservation.serviceImpl;

import com.example.busreservation.mapper.NodeMapper;
import com.example.busreservation.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeMapper mapper;

    @Override
    public boolean insertOrUpdateNode(String nodeid, String nodeno, String nodename, String gpslati, String gpslong) {
        return mapper.insertOrUpdateNode(nodeid, nodeno, nodename, gpslati, gpslong);
    }
}
