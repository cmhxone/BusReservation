package com.example.busreservation.schedule;

import com.example.busreservation.service.NodeService;
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

    /**
     * 도시 정보를 DB에 주기적으로 업데이트 한다
     */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleUpdateCity() {

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
                String nodeid, nodeno, nodenm, gpslati, gpslong;

                nodeid = map.get("nodeid");
                nodeno = map.get("nodeno");
                nodenm = map.get("nodenm");
                gpslati = map.get("gpslati");
                gpslong = map.get("gpslong");

                nodeService.insertOrUpdateNode(nodeid, nodeno, nodenm, gpslati, gpslong);
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

    }
}
