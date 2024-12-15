package com.example.solrDemo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UnNumberDto {
    private String id;
    private String name;
    private String code;
    private Boolean isGroup;
}