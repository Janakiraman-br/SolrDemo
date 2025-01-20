package com.example.solrDemo.api.controller;


import com.example.solrDemo.api.dto.UnNumberDto;
import com.example.solrDemo.api.service.DemoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {


    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;

    }

    @GetMapping("/getAll")
    public List<UnNumberDto> getAllUnNumbers() throws Exception {
        return demoService.getAllUnNumbers();
    }

    @GetMapping("/getAll/{name}")
    public List<UnNumberDto> getUnNumbers(@PathVariable String name) throws Exception
    {
      return demoService.getUnNumber(name);
    }
    @GetMapping("/getAll/{field}/{value}/{sortField}/{order}")
    public List<UnNumberDto> getUnNumbersAll(@PathVariable String field,
                                             @PathVariable String value,
                                             @PathVariable String sortField,
                                             @PathVariable String order) throws Exception
    {
        return demoService.getAllUnNumber(field,value,sortField,order);
    }
}
