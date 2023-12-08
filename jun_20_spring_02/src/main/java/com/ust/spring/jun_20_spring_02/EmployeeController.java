package com.ust.spring.jun_20_spring_02;

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
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        if (employee.getId()==null)
        {
            return er.save(employee);
        }
        Employee emp3=findByEmployeeId(employee.getId());
        if(emp3==null)
        {
            er.save(employee);
        }
        else
        {
            throw new AlreadyExistException("Id is already Exist");
        }
        return employee;
    }
    @GetMapping
    public List<Employee> retriveAllEmployee(){
        return er.findAll();
    }
    @GetMapping("/{id}")
    public Employee findByEmployeeId(@PathVariable("id")Integer id){
        Optional<Employee> temp = er.findById(id);
        Employee emp=null;
        if(temp.isPresent())
        {
            emp=temp.get();
        }
        return emp;
    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id")Integer id,@RequestBody Employee employee){
        Employee emp1=findByEmployeeId(id);
         if (emp1!=null)
        {
            emp1=employee;
            er.save(emp1);
        }
        return emp1;
    }
    @DeleteMapping("/{id}")
    public Employee deleteEmployee(@PathVariable("id")Integer id){
        Employee emp2=findByEmployeeId(id);
        if (emp2!=null)
        {
        er.delete(emp2);
        }
        return emp2;
    } 
}
