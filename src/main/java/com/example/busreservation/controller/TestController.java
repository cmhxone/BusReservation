package com.example.busreservation.controller;

import com.example.busreservation.service.NodeHeadToService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileReader;
import java.io.Reader;

@Slf4j
@Controller
public class TestController {

    @Autowired
    NodeHeadToService nodeHeadToService;

    @GetMapping("/test")
    @ResponseBody
    public Object getNodes() {

        try {
            Reader in = new FileReader("/Users/jomuhyeon/node.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
            for (CSVRecord record : records) {
                String nodeno = record.get(0);
                String headto = record.get(2);
                nodeHeadToService.insertOrUpdateNodeHeadTo(nodeno, headto);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return "Done";
    }
}
