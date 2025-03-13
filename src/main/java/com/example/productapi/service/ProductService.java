package com.example.productapi.service;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.dto.ProductResponse;

public interface ProductService {

    ProductDto createProduct(ProductDto ProductDto);

    ProductResponse getAllProducts(int pageNo, int pageSize);

    ProductDto findProductById(Integer id);

    ProductDto updateProduct(ProductDto ProductDto, int id);

    void deleteProduct(int id);
}
