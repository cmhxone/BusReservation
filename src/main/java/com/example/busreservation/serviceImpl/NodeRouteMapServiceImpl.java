package com.example.busreservation.serviceImpl;

import com.example.busreservation.mapper.NodeRouteMapMapper;
import com.example.busreservation.service.NodeRouteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NodeRouteMapServiceImpl implements NodeRouteMapService {

    @Autowired
    private NodeRouteMapMapper mapper;

    @Override
    public boolean updateNodeRouteMap(String nodeid, String routeid) {
        return mapper.updateNodeRouteMap(nodeid, routeid);
    }

    @Override
    public boolean deleteAllNodeRouteMap() {
        return mapper.deleteAllNodeRouteMap();
    }
}
