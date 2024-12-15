package com.example.solrDemo.api.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Bean
    public SolrClient solrClient() {
        // Replace with your actual Solr server URL
        String solrUrl = "http://localhost:8983/solr/unnumbers";
        return new HttpSolrClient.Builder(solrUrl).build();
    }
}
