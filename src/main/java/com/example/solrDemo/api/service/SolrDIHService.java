package com.example.solrDemo.api.service;

import com.example.solrDemo.api.models.UnNumber;
import com.example.solrDemo.api.models.UnNumbers;
import com.example.solrDemo.api.repository.UnNumberJpaRepository;
import com.example.solrDemo.api.repository.UnNumberSolrRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SolrDIHService {
    @Autowired
    private UnNumberJpaRepository unNumberJpaRepository;



    public String triggerFullImport() {
        List<UnNumbers> products = unNumberJpaRepository.findAll();

        // Map and index to Solr
        List<UnNumber> list = products.stream()
                .map(p -> new UnNumber(p.getId(), p.getName(), p.getCode(), p.getIsGroup())).toList();

        log.info(list.toString());
                  // list.forEach(unNumberSolrRepository::saveAll);


        log.info("Data successfully indexed to Solr!");
        return "Success";
    }

}
