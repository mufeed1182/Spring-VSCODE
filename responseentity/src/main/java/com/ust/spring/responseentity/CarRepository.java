package com.ust.spring.responseentity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer>{

    // @Query("SELECT e FROM Employee e WHERE e.age > :age")
    // List<Employee> findByAgeGreaterThan(@Param("age") int age);
    @Query("SELECT c FROM Car c WHERE c.name= :name")
     Car findByCarName(@Param("name") String name);

     @Query("SELECT c FROM Car c WHERE c.price > :price")
     List<Car> findByCarInRange(@Param("price") Double price);

}
