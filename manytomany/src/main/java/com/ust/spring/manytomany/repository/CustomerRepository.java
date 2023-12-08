package com.ust.spring.manytomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.spring.manytomany.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    
}
