package com.example.busreservation.schedule;

import com.example.busreservation.service.CityService;
import com.example.busreservation.service.NodeRouteMapService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ConfigurationProperties("data")
@Component
public class DatabaseSchedule {

    @Value("${service_key}")
    private String serviceKey;
    @Value("${city_code}")
    private String cityCode;

    @Autowired
    private NodeService nodeService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private CityService cityService;
    @Autowired
    private NodeRouteMapService nodeRouteMapService;

    @Scheduled(cron = "0 0 9-18/4 * * *")
    public void updateDB() {
        updateCityTable();
        updateNodeTable();
        updateRouteTable();
        updateNodeRouteMapTable();
    }

    /**
     * 도시 정보를 DB에 업데이트 한다
     */
    public void updateCityTable() {
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
            JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getCtyCodeList", serviceKey, parameter);
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
     * 정류장 정보를 DB에 업데이트 한다
     */
    public void updateNodeTable() {

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
            JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getSttnNoList", serviceKey, parameter);
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
     * 노선 정보를 DB에 업데이트 한다
     */
    public void updateRouteTable() {
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
            JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList", serviceKey, parameter);
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

    /**
     * 노선이 지나가는 정류장 정보를 DB에 업데이트 한다
     */
    @Transactional
    public void updateNodeRouteMapTable() {
        log.info("Prepare to update 'ROUTE' table");
        // 모든 노선 정보를 DB에서 추출
        List<Map<String, Object>> routes = routeService.getAllRoutes();
        RESTUtil restUtil = new RESTUtil();

        // JSON 분석 시, 사용할 속성 값
        ArrayList<String> properties = new ArrayList<>();
        properties.add("routeid");

        log.info("Trying to delete all columns from 'ROUTE' table");
        // DB 정보를 일괄 삭제한다
        nodeRouteMapService.deleteAllNodeRouteMap();
        log.info("Deleted all columns from 'ROUTE' table");

        log.info("Trying to update 'ROUTE' table");
        // 각 노선에 해당하는 정류장 값을 DB에 저장한다
        for (Map<String, Object> route : routes) {
            // REST 요청 파라미터 값
            HashMap<String, String> params = new HashMap<>();
            params.put("numOfRows", "10000");
            params.put("cityCode", cityCode);
            params.put("routeId", route.get("ROUTEID").toString());

            // REST 호출 시도
            try {
                JSONArray jsonRequest = restUtil.getJSONRequest("http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList", serviceKey, params);
                for (int j=0; j<jsonRequest.length(); j++) {
                    // 데이터 저장 시도
                    nodeRouteMapService.updateNodeRouteMap(jsonRequest.getJSONObject(j).getString("nodeid").toString(), route.get("ROUTEID").toString());
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
                log.error("Error occured! Rollbacking whole changes");
                // 자동 롤백이 일어난다
            }
        }
        log.info("Successfully updated 'ROUTE' table");
    }
}
