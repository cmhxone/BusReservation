package com.example.busreservation.url;

public class APIURL {

    public static final String GET_NODE_ARR_ROUTE = "http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList";                 // 정류장 도착 노선 정보
    public static final String GET_CITY_CODE = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getCtyCodeList";                                // 도시 코드
    public static final String GET_NODE_LIST = "http://openapi.tago.go.kr/openapi/service/BusSttnInfoInqireService/getSttnNoList";                                  // 정류장 정보
    public static final String GET_ROUTE_LIST = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteNoList";                               // 노선 정보
    public static final String GET_ROUTE_THROUGH_NODE_LIST = "http://openapi.tago.go.kr/openapi/service/BusRouteInfoInqireService/getRouteAcctoThrghSttnList";      // 정류장이 지나는 노선 정보

}
