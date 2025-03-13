package com.example.productapi.service.impl;



import com.example.productapi.dto.ReviewDto;
import com.example.productapi.repository.ReviewRepository;
import com.example.productapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        return null;
    }
}
