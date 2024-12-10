package com.example.solrDemo.api.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "unnumber")
public class UnNumber {
    @Id
    @Field
    private String id;
    @Field
    private String name;
    @Field
    private String code;
    @Field
    private Boolean isGroup;

}
