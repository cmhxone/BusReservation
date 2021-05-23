package com.example.busreservation.controller;

import com.example.busreservation.dto.Node;
import com.example.busreservation.mapper.NodeHeadToMapper;
import com.example.busreservation.service.NodeHeadToService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/passenger")
@Controller
public class PassangerController {

    @Autowired
    NodeHeadToService nodeHeadToService;

    @GetMapping("")
    public String defaultPage(Model model) {

        List<Node> nodes = nodeHeadToService.getAllNodeHeadToMap();
        List<String> nodelist = new ArrayList<>();

        for (Node node : nodes) {
            Object nodename = node.getNodename();
            Object headto = node.getHeadto();
            String headtostr = headto != null ? headto.toString() : "";
            headtostr += headtostr.isEmpty() ? "" : " 방면";

            nodelist.add(nodename + " " + headtostr);
        }

        model.addAttribute("nodes", nodelist);

        return "passenger/passenger";
    }
}
