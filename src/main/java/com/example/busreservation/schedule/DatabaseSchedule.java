package com.example.busreservation.schedule;

import com.example.busreservation.service.CityService;
import com.example.busreservation.service.NodeService;
import com.example.busreservation.service.RouteService;
import com.example.busreservation.util.RESTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@ConfigurationProperties("data")
@Component
public class DatabaseSchedule {

    @Value("${service_key}")
    private String service_key;

    @Autowired
    private NodeService nodeService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CityService cityService;

    /**
     * 도시 정보를 DB에 주기적으로 업데이트 한다
     */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleUpdateCity() {
    	log.info("Prepare to update 'CITY' table");
        // REST 요청을 위한 파라미터 정보
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("numOfRows", "10000");

        // REST 응답을 분석하기 위한 파라미터 정보
        ArrayList<String> property = new ArrayList<>();
        property.add("citycode");
        property.add("cityname");

        log.info("Trying to update 'CITY' table");
        // REST 호출 시도
        RESTUtil restUtil = new RESTUtil();
        try {
            JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getCtyCodeList", service_key, parameter);
            HashMap<String, HashMap<String, String>> jsonResponse = restUtil.getMapData(jsonRequest, property);

            // JSON 응답을 분석해 DB 업데이트
            jsonResponse.forEach((index, map) -> {
                ArrayList<String> param = new ArrayList<>();

                // Property 값으로 Map 분해 후 ArrayList에 할당
                for (String prop : property) {
                	param.add(map.get(prop));
                }

                // Update 서비스 호출
                cityService.insertOrUpdateCity(param.get(0), param.get(1));
            });

            log.info("Successfully updated 'CITY' table");
        } catch (Exception e) {
            log.error("Failed to update 'CITY' table");
            e.printStackTrace(System.err);
        }
    }

    /**
     * 정류장 정보를 DB에 주기적으로 업데이트 한다
     */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleUpdateNode() {

        log.info("Prepare to update 'NODE' table");
        // REST 요청을 위한 파라미터 정보
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("cityCode", "38030");
        parameter.put("numOfRows", "10000");

        // REST 응답을 분석하기 위한 파라미터 정보
        ArrayList<String> property = new ArrayList<>();
        property.add("nodeid");
        property.add("nodeno");
        property.add("nodenm");
        property.add("gpslati");
        property.add("gpslong");

        log.info("Trying to update 'NODE' table");
        // REST 호출 시도
        RESTUtil restUtil = new RESTUtil();
        try {
            JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getSttnNoList", service_key, parameter);
            HashMap<String, HashMap<String, String>> jsonResponse = restUtil.getMapData(jsonRequest, property);

            // JSON 응답을 분석해 DB 업데이트
            jsonResponse.forEach((index, map) -> {
                ArrayList<String> param = new ArrayList<>();

                // Property 값으로 Map 분해 후 ArrayList에 할당
                for (String prop : property) {
                	param.add(map.get(prop));
                }

                // Update 서비스 호출
                nodeService.insertOrUpdateNode(param.get(0), param.get(1), param.get(2), param.get(3), param.get(4));
            });

            log.info("Successfully updated 'NODE' table");
        } catch (Exception e) {
            log.error("Failed to update 'NODE' table");
            e.printStackTrace(System.err);
        }
    }

    /**
     * 노선 정보를 DB에 주기적으로 업데이트 한다
     */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleUpdateRoute() {
    	log.info("Prepare to update 'ROUTE' table");
        // REST 요청을 위한 파라미터 정보
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("cityCode", "38030");
        parameter.put("numOfRows", "10000");

        // REST 응답을 분석하기 위한 파라미터 정보
        ArrayList<String> property = new ArrayList<>();
        property.add("routeid");
        property.add("routeno");
        property.add("routetp");
        property.add("startnodenm");
        property.add("endnodenm");
        property.add("startvehicletime");
        property.add("endvehicletime");
        
        log.info("Trying to update 'ROUTE' table");
        // REST 호출 시도
        RESTUtil restUtil = new RESTUtil();
        try {
            JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList", service_key, parameter);
            HashMap<String, HashMap<String, String>> jsonResponse = restUtil.getMapData(jsonRequest, property);
            
            // JSON 응답을 분석해 DB 업데이트
            jsonResponse.forEach((index, map) -> {
                ArrayList<String> param = new ArrayList<>();
                
                // Property 값으로 Map 분해 후 ArrayList에 할당
                for (String prop : property) {
                	param.add(map.get(prop));
                }

                // Update 서비스 호출
                routeService.insertOrUpdateRoute(param.get(0), param.get(1), param.get(2), param.get(3), param.get(4), param.get(5), param.get(6));
            });

            log.info("Successfully updated 'ROUTE' table");
        } catch (Exception e) {
            log.error("Failed to update 'ROUTE' table");
            e.printStackTrace(System.err);
        }
    }
}
