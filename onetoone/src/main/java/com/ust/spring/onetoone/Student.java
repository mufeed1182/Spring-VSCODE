package com.ust.spring.onetoone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @OneToOne
    private Laptop laptops;  

    public Student(){}

    public Student(Integer id, String name, Laptop laptops) {
        this.id = id;
        this.name = name;
        this.laptops = laptops;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Laptop getLaptops() {
        return laptops;
    }

    public void setLaptops(Laptop laptops) {
        this.laptops = laptops;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", laptops=" + laptops + "]";
    }
    
}
