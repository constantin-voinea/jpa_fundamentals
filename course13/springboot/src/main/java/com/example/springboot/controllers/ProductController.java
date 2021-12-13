package com.example.springboot.controllers;

import com.example.springboot.entities.Product;
import com.example.springboot.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cvoinea
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add/{name}")
    public void addProduct(@PathVariable(name = "name") String name) {
        productService.addProduct(name);
    }

    @GetMapping("/get")
    public List<Product> findProducts() {
        return productService.findProducts();
    }
}
