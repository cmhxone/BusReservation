package com.example.busreservation.serviceImpl;

import com.example.busreservation.mapper.NodeHeadToMapper;
import com.example.busreservation.service.NodeHeadToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeHeadToServiceImpl implements NodeHeadToService {

    @Autowired
    NodeHeadToMapper mapper;

    @Override
    public boolean insertOrUpdateNodeHeadTo(String nodeno, String headto) {
        return mapper.insertOrUpdateNodeHeadTo(nodeno, headto);
    }
}
