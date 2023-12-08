package com.ust.spring.mainclass;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository er;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return er.save(employee);
    }
    @GetMapping
    public List<Employee> retriveAllEmployee(){
        return er.findAll();
    }
    @GetMapping("/{id}")
    public Employee findByEmployeeId(@PathVariable Integer id){
        Optional<Employee> temp = er.findById(id);
        Employee emp=null;
        if (temp.isPresent())
        {
            emp=temp.get();
        }
        return emp;
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
        Employee emp = findByEmployeeId(id);
        if (emp!=null)
        {
            emp=employee;
            er.save(emp);
        }
        return emp;
    }
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable Integer id){
        Employee emp = findByEmployeeId(id);
        if(emp!=null)
        {
            er.delete(emp);
        }
        return emp;
    }

    
}
