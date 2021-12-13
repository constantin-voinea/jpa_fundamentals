package com.example.springboot.services;

import com.example.springboot.entities.Product;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cvoinea
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(String name) {
        Product product = new Product();
        product.setName(name);
        productRepository.save(product);
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }
}
