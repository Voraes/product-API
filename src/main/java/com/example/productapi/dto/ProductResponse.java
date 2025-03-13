package com.example.productapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private List<ProductDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
