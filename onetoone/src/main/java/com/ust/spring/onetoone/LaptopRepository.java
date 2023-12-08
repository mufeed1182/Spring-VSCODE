package com.ust.spring.onetoone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository  extends JpaRepository<Laptop,Integer> {
    
}
