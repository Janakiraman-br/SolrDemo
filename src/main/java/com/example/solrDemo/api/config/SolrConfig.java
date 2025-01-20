package com.example.solrDemo.api.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

    @Bean
    public SolrClient solrClient() {
        // Replace with your actual Solr server URL
        String solrUrl = "https://athena-solr-cdt.maersk-digital.net/solr/unnumbers";
        return new HttpSolrClient.Builder(solrUrl).build();
    }
}
