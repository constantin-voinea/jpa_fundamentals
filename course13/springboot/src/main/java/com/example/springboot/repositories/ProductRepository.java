package com.example.springboot.repositories;

import com.example.springboot.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cvoinea
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {


}
