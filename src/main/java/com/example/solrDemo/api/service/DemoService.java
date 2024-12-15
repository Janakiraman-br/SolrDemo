package com.example.solrDemo.api.service;

import com.example.solrDemo.api.dto.UnNumberDto;
import com.example.solrDemo.api.models.UnNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class DemoService {

    private final SolrClient solrClient;


    public List<UnNumberDto> getAllUnNumbers() throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        query.setRows(10);

        QueryResponse response = solrClient.query(query);
        log.info(String.valueOf(response));

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
        log.info(String.valueOf(response));

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
        log.info(String.valueOf(response));

        return response.getBeans(UnNumber.class).stream()
                .map(unNumber -> UnNumberDto.builder()
                        .id(unNumber.getId())
                        .name(unNumber.getName())
                        .code(unNumber.getCode())
                        .isGroup(unNumber.getIsGroup())
                        .build()).collect(Collectors.toList());
    }
}
