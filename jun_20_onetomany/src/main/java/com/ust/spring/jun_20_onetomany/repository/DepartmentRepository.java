package com.ust.spring.jun_20_onetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.spring.jun_20_onetomany.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    
}
