package com.example.busreservation.serviceImpl;

import com.example.busreservation.dto.Node;
import com.example.busreservation.mapper.NodeHeadToMapper;
import com.example.busreservation.service.NodeHeadToService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeHeadToServiceImpl implements NodeHeadToService {

    @Autowired
    NodeHeadToMapper mapper;

    @Override
    public boolean insertOrUpdateNodeHeadTo(String nodeno, String headto) {
        return mapper.insertOrUpdateNodeHeadTo(nodeno, headto);
    }

    @Override
    public List<Node> getAllNodeHeadToMap() {
        return mapper.getAllNodeHeadToMap();
    }

    @Override
    public List<Node> getNodeHeadToMap(int page, String nodename) {
        return mapper.getNodeHeadToMap(page, nodename);
    }

	@Override
	public Node getNodeHeadToMapByNodeID(String nodeid) {
		return mapper.getNodeHeadToMapByNodeID(nodeid);
	}
    
}
