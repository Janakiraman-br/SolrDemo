package com.example.solrDemo.api.service;

import com.example.solrDemo.api.dto.UnNumberDto;
import com.example.solrDemo.api.models.UnNumber;
import com.example.solrDemo.api.repository.UnNumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DemoService {
    @Autowired
    private UnNumberRepository unNumberRepository;
    @Autowired
    private  SolrClient solrClient;

//    public Iterable<UnNumber> getAllUnNumbers() {
//        Iterable<UnNumber> all = unNumberRepository.findAll();
//        System.out.println(all);
//        return all;
//
//    }



public List<UnNumberDto> getAllUnNumbers() throws Exception {
    SolrQuery query = new SolrQuery("*:*");
    query.setRows(10);

    QueryResponse response = solrClient.query(query);
    System.out.println(response);
    
    return response.getBeans(UnNumber.class).stream()
            .map(unNumber -> UnNumberDto.builder()
                    .id(unNumber.getId())
                    .name(unNumber.getName())
                    .code(unNumber.getCode())
                    .isGroup(unNumber.getIsGroup())
                    .build()).collect(Collectors.toList());
}


    public List<UnNumberDto> getUnNumber(String name) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery("name:" + name);
        query.setRows(10);

        QueryResponse response = solrClient.query(query);
        System.out.println(response);
        return response.getBeans(UnNumber.class).stream()
                .map(unNumber -> UnNumberDto.builder()
                        .id(unNumber.getId())
                        .name(unNumber.getName())
                        .code(unNumber.getCode())
                        .isGroup(unNumber.getIsGroup())
                        .build()).collect(Collectors.toList());
}

    public List<UnNumberDto> getAllUnNumber(String name) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery("name:*" + name + "*");
        query.setRows(10);

        QueryResponse response = solrClient.query(query);
        System.out.println(response);

        return response.getBeans(UnNumber.class).stream()
                .map(unNumber -> UnNumberDto.builder()
                        .id(unNumber.getId())
                        .name(unNumber.getName())
                        .code(unNumber.getCode())
                        .isGroup(unNumber.getIsGroup())
                        .build()).collect(Collectors.toList());
    }
}
