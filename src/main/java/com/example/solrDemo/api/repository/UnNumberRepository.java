package com.example.solrDemo.api.repository;


import com.example.solrDemo.api.models.UnNumber;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnNumberRepository extends SolrCrudRepository<UnNumber, String> {
    UnNumber findByName(String name);
}
