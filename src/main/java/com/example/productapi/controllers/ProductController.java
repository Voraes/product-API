package com.example.productapi.controllers;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.dto.ReviewDto;
import com.example.productapi.service.ProductService;
import com.example.productapi.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ProductController {

    private final ProductService productService;
    private final ReviewService reviewService;

    @Autowired
    public ProductController(ProductService productService, ReviewService reviewService) {
        this.productService = productService;
        this.reviewService = reviewService;
    }


    @GetMapping("product")
    public ResponseEntity<ProductResponse> getProducts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(productService.getAllProducts(pageNo, pageSize));
    }

    @GetMapping("product/{id}")
    public ResponseEntity<ProductDto> productDetail(@PathVariable("id") int id) {
        //return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping("product/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("product/{id}/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable("id") int productId) {
        return ResponseEntity.ok(productService.updateProduct(productDto, productId));
    }

    @DeleteMapping("product/{id}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted");
    }

    //Review Methods
    @PostMapping("review/create")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.createReview(reviewDto));
    }
}
