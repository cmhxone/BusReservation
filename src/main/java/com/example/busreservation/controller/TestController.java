package com.example.busreservation.controller;

import com.example.busreservation.service.NodeRouteMapService;
import com.example.busreservation.service.RouteService;
import com.example.busreservation.util.RESTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@ConfigurationProperties("data")
@Slf4j
@Controller
public class TestController {

    @Value("${service_key}")
    private String serviceKey;
    @Value("${city_code}")
    private String cityCode;

    @Autowired
    private RouteService routeService;
    @Autowired
    private NodeRouteMapService nodeRouteMapService;

    @GetMapping("/test")
    @ResponseBody
    public List<Map<String, Object>> getNodes() {

        List<Map<String, Object>> routes = routeService.getAllRoutes();

        return routes;
    }
}
