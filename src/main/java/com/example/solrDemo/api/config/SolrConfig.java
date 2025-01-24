package com.example.solrDemo.api.config;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SolrConfig {
    private static final String SOLR_BASE_URL = "https://athena-solr-cdt.maersk-digital.net/solr";
    private final Map<String, SolrClient> solrClients = new HashMap<>();

    public SolrClient getSolrClient(String collectionName) {
        if (!solrClients.containsKey(collectionName)) {
            String solrUrl = SOLR_BASE_URL + "/" + collectionName;
            SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
            solrClients.put(collectionName, solrClient);
        }
        return solrClients.get(collectionName);
    }
}

