package com.example.productapi.repository;

import com.example.productapi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProductId(int productId);
}
