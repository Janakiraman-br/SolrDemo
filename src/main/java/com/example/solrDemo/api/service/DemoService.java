package com.example.solrDemo.api.service;

import com.example.solrDemo.api.dto.UnNumberDto;
import com.example.solrDemo.api.models.UnNumber;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.request.SolrQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j

public class DemoService {

    private final SolrClient solrClient;
    private static final String SOLR_URL = "http://localhost:8983/solr";  // Update with your Solr URL
    private final RestTemplate restTemplate;

    public DemoService(SolrClient solrClient, RestTemplate restTemplate) {
        this.solrClient = solrClient;
        this.restTemplate = restTemplate;
    }


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

    public String triggerFullImport(String coreName) throws Exception {
        String url = SOLR_URL + "/" + coreName + "/dataimport?command=full-import";

        try {

            return restTemplate.getForObject(url, String.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to trigger full import: " + e.getMessage());
        }
    }

}
