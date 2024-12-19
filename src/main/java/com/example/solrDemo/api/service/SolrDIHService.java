package com.example.solrDemo.api.service;

import com.example.solrDemo.api.models.UnNumber;
import com.example.solrDemo.api.models.UnNumbers;
import com.example.solrDemo.api.repository.UnNumberJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SolrDIHService {
    @Autowired
    private UnNumberJpaRepository unNumberJpaRepository;
    @Autowired
    private SolrClient solrClient;

    public String triggerFullImport() {
        List<UnNumbers> products = unNumberJpaRepository.findAll();

        // Map to SolrInputDocument
//        List<SolrInputDocument> solrDocuments = products.stream().map(product -> {
//            SolrInputDocument doc = new SolrInputDocument();
//            doc.addField("id", product.getId());
//            doc.addField("name", product.getName());  // Ensure this is a single value, not a list
//            doc.addField("code", product.getCode());  // Ensure this is a single value, not a list
//            doc.addField("is_group", product.getIsGroup());  // Ensure this is a single value, not a list
//            return doc;
//        }).toList();

        List<UnNumber> solrDocuments = products.stream().map(product -> {
            UnNumber doc = new UnNumber();
            doc.setId(product.getId());
            doc.setName(product.getName());
            doc.setCode(product.getCode());
            doc.setIsGroup(product.getIsGroup());
            return doc;
        }).toList();


        log.info("Mapped Solr documents: {}", solrDocuments);

        try {
            solrClient.addBeans(solrDocuments);
            solrClient.commit();
            log.info("Data successfully indexed to Solr!");
            return "Success";
        } catch (Exception e) {
            log.error("Error indexing data to Solr", e);
            return "Failed to index data: " + e.getMessage();
        }
    }

}