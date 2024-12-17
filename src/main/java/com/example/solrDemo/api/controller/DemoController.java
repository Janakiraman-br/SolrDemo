package com.example.solrDemo.api.controller;


import com.example.solrDemo.api.dto.UnNumberDto;
import com.example.solrDemo.api.service.DemoService;
import com.example.solrDemo.api.service.SolrDIHService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {


    private final DemoService demoService;
    private final SolrDIHService solrDIHService;

    public DemoController(DemoService demoService, SolrDIHService solrDIHService) {
        this.demoService = demoService;
        this.solrDIHService = solrDIHService;
    }


    @GetMapping("/getAll/{name}")
    public List<UnNumberDto> getUnNumbers(@PathVariable String name) throws Exception
    {
      return demoService.getUnNumber(name);
    }
    @GetMapping("/getAll/all/{name}")
    public List<UnNumberDto> getUnNumbersAll(@PathVariable String name) throws Exception
    {
        return demoService.getAllUnNumber(name);
    }
    @GetMapping("/getAll")
    public List<UnNumberDto> getAllUnNumbers() throws Exception {
        return demoService.getAllUnNumbers();
    }

    @GetMapping("/trigger")
    public ResponseEntity<String> triggerDIH() {
        String response = solrDIHService.triggerFullImport();
        return ResponseEntity.ok(response);
    }

}
