package com.example.busreservation.controller;

import com.example.busreservation.dto.Node;
import com.example.busreservation.service.NodeHeadToService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequestMapping("/passenger")
@Controller
public class PassangerController {

    @Autowired
    NodeHeadToService nodeHeadToService;

    @GetMapping("")
    public String defaultPage(@RequestParam(name = "page", required = true, defaultValue = "0") int page,
                              @RequestParam(name = "searchNodeName", required = false, defaultValue = "") String searchNodeName,
                              Model model) {

        int prevpage, nextpage;
        // 페이지 한계 지정
        if (page < 0) {
            page = 0;
        }

        // 리스트에 정류장 노드(방향 포함)들을 가져온다
        List<Node> nodes = nodeHeadToService.getNodeHeadToMap(page, searchNodeName);

        // 페이지 계산용
        prevpage = page-1;
        nextpage = page+1;

        // 모델에 애트리뷰트 추가
        model.addAttribute("nodes", nodes);
        model.addAttribute("prevpage", prevpage);
        model.addAttribute("currentpage", page);
        model.addAttribute("nextpage", nextpage);
        model.addAttribute("searchNodeName", searchNodeName);

        return "passenger/passenger";
    }
}
