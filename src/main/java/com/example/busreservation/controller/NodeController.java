package com.example.busreservation.controller;

import com.example.busreservation.dto.Node;
import com.example.busreservation.repository.ReservationRepository;
import com.example.busreservation.service.NodeHeadToService;
import com.example.busreservation.url.APIURL;
import com.example.busreservation.util.RESTUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@ConfigurationProperties("data")
@Slf4j
@RequestMapping("/node")
@Controller
public class NodeController {
	
	@Value("${service_key}")
	private String serviceKey;
	@Value("${city_code}")
	private String cityCode;

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private NodeHeadToService nodeHeadToService;

	@GetMapping("/{nodeid}")
	public String getNode(@PathVariable(name = "nodeid", required = true) String nodeid,
						  HttpServletRequest http,
						  Model model) {

		// 결과값 존재 여부 확인을 위해
		boolean result = false;
		Node node = nodeHeadToService.getNodeHeadToMapByNodeID(nodeid);
		model.addAttribute("node", node);
		
		// REST 요청 설정
		RESTUtil restUtil = new RESTUtil();
		String url = APIURL.GET_NODE_ARR_ROUTE;
		HashMap<String, String> params = new HashMap<>();
		ArrayList<String> properties = new ArrayList<>();
		
		// REST 요청 URL 파라미터 설정
		params.put("numOfRows", "100");
		params.put("cityCode", cityCode);
		params.put("nodeId", nodeid);
		
		// REST 응답 JSON 속성 설정(분해용)
		properties.add("nodenm");
		properties.add("routetp");
		properties.add("arrprevstationcnt");
		properties.add("routeid");
		properties.add("vehicletp");
		properties.add("routeno");
		properties.add("arrtime");
		
		// REST 요청 시도
		try {
			JSONArray jsonRequest = restUtil.getJSONRequest(url, serviceKey, params);
			
			if (jsonRequest != null) {
				HashMap<String, HashMap<String, String>> jsonResponse = restUtil.getMapData(jsonRequest, properties);
				ArrayList<HashMap<String, String>> routes = new ArrayList<HashMap<String,String>>(); 
				
				jsonResponse.forEach((index, map) -> {
					Integer arrtime = Integer.parseInt(map.get("arrtime"));
					arrtime /= 60;
					map.replace("arrtime", arrtime.toString());
					if (reservationRepository.getReservation().contains(map.get("routeno"))) {
						map.put("reserved", "true");
					} else {
						map.put("reserved", "false");
					}
					routes.add(map);
				});
				
				model.addAttribute("routes", routes);
				
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		model.addAttribute("result", result);
		model.addAttribute("nodeid", nodeid);
		
		return "node/node";
	}

	@GetMapping("/{nodeid}/refresh")
	public String refreshNode(@PathVariable(name = "nodeid", required = true) String nodeid,
							  HttpServletRequest http,
							  Model model) {
		return getNode(nodeid, http, model) + ":: #arrRoutes";
	}
}
