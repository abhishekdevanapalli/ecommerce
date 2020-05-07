package com.ecommerce.spring.boot.ecommerce.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.spring.boot.ecommerce.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

