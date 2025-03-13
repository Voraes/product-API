package com.example.productapi.service.impl;

import com.example.productapi.dto.ProductDto;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.exceptions.ProductNotFoundException;
import com.example.productapi.models.Product;
import com.example.productapi.repository.ProductRepository;
import com.example.productapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

/*    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setType(productDto.getType());

        productRepository.save(product);

        return product;
    }*/

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setType(productDto.getType());

        Product newProduct = productRepository.save(product);

        ProductDto productResponse = new ProductDto();
        productResponse.setId(newProduct.getId());
        productResponse.setName(newProduct.getName());
        productResponse.setType(newProduct.getType());
        return productResponse;
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProduct = products.getContent();
        List<ProductDto> content = listOfProduct.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

    @Override
    public ProductDto findProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(("Product could not be found")));
        return mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product could not be updated"));
        product.setName(productDto.getName());
        product.setType(productDto.getType());
        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product could not be deleted"));
        productRepository.delete(product);
        return;
    }


    private ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setType(product.getType());
        return productDto;
    }

    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setType(productDto.getType());
        return product;
    }
}
