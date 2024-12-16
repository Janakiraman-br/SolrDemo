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
    @Field(value = "id")
    private String id;
    @Field(value = "name")
    private String name;
    @Field(value = "code")
    private String code;
    @Field(value = "is_group")
    private Boolean isGroup;

}
