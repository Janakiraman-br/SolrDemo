package com.example.solrDemo.api.service;

import com.example.solrDemo.api.config.SolrConfig;
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

    private final SolrConfig solrConfig;

    public List<UnNumberDto> getAllUnNumbers() throws Exception {
        SolrClient solrClient = solrConfig.getSolrClient("commodity");

        SolrQuery query = new SolrQuery("*:*");
        query.setStart(20);
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
        SolrClient solrClient = solrConfig.getSolrClient("commodity");
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


    public List<UnNumberDto> getAllUnNumber(String field,String value,String sortField,String order) throws Exception {
        SolrClient solrClient = solrConfig.getSolrClient("commodity");
        SolrQuery query = new SolrQuery();
        query.setQuery(field + ":*" + value + "*");
        query.setRows(2300);
        if (order.equalsIgnoreCase("asc"))
        {
            query.setSort(sortField, SolrQuery.ORDER.asc);
        }
        else
        {
            query.setSort(sortField, SolrQuery.ORDER.desc);
        }

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
