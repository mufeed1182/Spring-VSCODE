package com.ust.spring.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.spring.manytomany.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    
}
