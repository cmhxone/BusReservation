package com.example.busreservation.util;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST 요청 관련 유틸리티<br><br>
 * <hr>
 * getJSONRequest(URL, serviceKey, HashMap&lt;String, String&gt; Param);<br>
 */
@Component
public class RESTUtil {

    /**
     *
     * REST 요청값을 JSONArray로 반환하는 메소드
     *
     * @param url           REST 요청 기본 URL
     * @param serviceKey    REST 요청 서비스 키
     * @param params        REST 요청 추가 URL 파라미터
     * @return              요청값의 JSONArray(response.body.items.item[])
     * @throws UnsupportedEncodingException
     * @throws JSONException
     */
    public JSONArray getJSONRequest(String url, String serviceKey, @Nullable Map<String, String> params) throws UnsupportedEncodingException, JSONException {
        JSONArray result;
        JSONObject reqResJSON;
        ResponseEntity<?> resultMap;

        // REST 요청 규격 선언
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // 타임아웃 지정
        factory.setConnectionRequestTimeout(2000);
        factory.setReadTimeout(2000);
        factory.setConnectTimeout(2000);
        // REST 요청을 전송할 템플릿을 지정한다.
        RestTemplate restTemplate = new RestTemplate(factory);

        // 파라미터와 서비스 키를 대입해 호출 URL을 만든다.
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        params.forEach((key, value) -> {
            try {
                urlBuilder.append("&" + URLEncoder.encode(key, "UTF-8") + "=" + value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        URI uri = URI.create(urlBuilder.toString());

        // REST 요청 전송
        resultMap = restTemplate.getForEntity(uri, Map.class);

        // JSON 데이터 중 필요부분만 추출 후 반환한다.
        reqResJSON = new JSONObject((Map<?, ?>)resultMap.getBody());
        reqResJSON = reqResJSON.getJSONObject("response");
        reqResJSON = reqResJSON.getJSONObject("body");
        reqResJSON = reqResJSON.getJSONObject("items");
        result = reqResJSON.getJSONArray("item");

        return result;
    }

    /**
     *
     * REST 요청 결과값에서 파라미터로 전송한 속성에 해당하는 값을 해쉬맵으로 반환한다
     *
     * @param jsonArray         REST 요청 결과
     * @param propertyList      JSON의 속성 이름
     * @return                  인덱스와 해당데이터 해쉬맵
     * @throws JSONException
     */
    public HashMap<String, HashMap<String, String>> getMapData(JSONArray jsonArray, List<String> propertyList) throws JSONException {
        HashMap<String, HashMap<String, String>> result = new HashMap<>();

        // JSONArray를 순회하며 Propety에 해당하는 결과(내부해쉬맵)에 저장한다
        for (Integer i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            HashMap<String, String> jsonData = new HashMap<>();
            
            // 내부 해쉬맵에 저장하는 부분
            for (String property : propertyList) {
            	try {
            		jsonData.put(property, jsonObject.getString(property));
            	} catch (JSONException e) {
            		jsonData.put(property, "");
            	}
            }

            // 결과 값(외부해쉬맵)에 값을 전달
            result.put(i.toString(), jsonData);
        }

        return result;
    }
}
