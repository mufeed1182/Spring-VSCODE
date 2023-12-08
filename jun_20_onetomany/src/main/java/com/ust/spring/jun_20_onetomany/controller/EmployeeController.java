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

import com.ust.spring.jun_20_onetomany.entity.Employee;
import com.ust.spring.jun_20_onetomany.repository.EmployeeRepository;

class AlreadyExistException extends RuntimeException
{
    public AlreadyExistException(String message)
    {
        super(message);
    }
}

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository er;

    public Employee addEmployee(@RequestBody Employee employee){
        if (employee.getId()==null)
        {
            return er.save(employee);
        }
        Employee temp = FindEmployeeById(employee.getId());
        if (temp==null)
        {
            er.save(employee);
        }
        else{
            throw new AlreadyExistException("Already exist");
        }
        return employee;

    }
    @GetMapping
    public List<Employee> retrievAllEmployees(){
        return er.findAll();
    }
    @GetMapping("/{id}")
    public Employee FindEmployeeById(@PathVariable("id")Integer id){
        Optional<Employee> temp = er.findById(id);
        Employee emp=null;
        if (temp.isPresent())
        {
            emp=temp.get();
        }
        return emp;
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id")Integer id, @RequestBody Employee employee){
        Employee emp = FindEmployeeById(id);
        if(emp!=null)
        {
            emp=employee;
            er.save(emp);
        }
        return emp;
    }
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable("id")Integer id){
        Employee emp = FindEmployeeById(id);
        if(emp!=null)
        {
            er.delete(emp);
        }
        return emp;
    }

    
}
