package com.example.busreservation.service;

import com.example.busreservation.dto.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NodeHeadToService {

    public boolean insertOrUpdateNodeHeadTo(String nodeno, String headto);
    public List<Node> getAllNodeHeadToMap();
}
