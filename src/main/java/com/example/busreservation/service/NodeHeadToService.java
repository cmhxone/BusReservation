package com.example.busreservation.service;

import org.springframework.stereotype.Service;

@Service
public interface NodeHeadToService {

    public boolean insertOrUpdateNodeHeadTo(String nodeno, String headto);
}
