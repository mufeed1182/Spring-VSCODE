package com.ust.spring.jun_20_onetomany.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.spring.jun_20_onetomany.entity.Department;
import com.ust.spring.jun_20_onetomany.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository dr;

    public void addDepartment(@RequestBody Department department){}
    @GetMapping
    public List<Department> retrievAllDepartment(){
        return dr.findAll();
    }
    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id")Integer id){
        Optional<Department> temp = dr.findById(id);
        Department dpt=null;
        if(temp.isPresent())
        {
            dpt=temp.get();
        }
        return dpt; 
    }
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable("id")Integer id,@RequestBody Department department){
        Department dpt = findDepartmentById(id);
        if(dpt!=null)
        {
            dpt=department;
            dr.save(dpt);
        }
        return dpt;
    }
    @DeleteMapping("/{id}")
    public Department deleteDepartment(@PathVariable("id")Integer id){
        Department dpt=findDepartmentById(id);
        if(dpt!=null)
        {
            dr.delete(dpt);
        }
        return dpt;
    }
    
}
