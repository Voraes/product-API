package com.example.productapi.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private int id;
    private String title;
    private String content;
    private int stars;
}
