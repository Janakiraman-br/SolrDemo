package com.example.solrDemo.api.controller;


import com.example.solrDemo.api.models.UnNumber;
import com.example.solrDemo.api.repository.UnNumberRepository;
import com.example.solrDemo.api.service.DemoService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DemoController {


    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }


    @GetMapping("/getAll/{name}")
    public List<UnNumber> getUnNumbers(@PathVariable String name) throws Exception
    {
      return demoService.getUnNumber(name);
    }
    @GetMapping("/getAll")
    public Iterable<UnNumber> getAllUnNumbers() throws Exception {
        return demoService.getAllUnNumbers();
    }

}
