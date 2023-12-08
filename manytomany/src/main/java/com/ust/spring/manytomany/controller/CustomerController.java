package com.ust.spring.manytomany.controller;

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

import com.ust.spring.manytomany.entity.Customer;
import com.ust.spring.manytomany.repository.CustomerRepository;

class Already extends RuntimeException
{
    public Already(String msg)
    {
        super(msg);
    }
}

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerRepository cr;

    public Customer addCustomer(@RequestBody Customer customer){
        if (customer.getId()==null)
        {
            return cr.save(customer);
        }
        Customer cmr = findCustomerById(customer.getId());
        if (cmr==null)
        {
            cr.save(customer);
        }else{
            throw new Already("Already Exists");
        }

        return customer;
    }
    @GetMapping
    public List<Customer> retirveAllCustomer(){
        return cr.findAll();
    }
    @GetMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id")Integer id){
        Optional<Customer> temp = cr.findById(id);
        Customer cmr=null;
        if (temp.isPresent())
        {
            cmr=temp.get();
        }
        return cmr;
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id")Integer id,@RequestBody Customer customer){
        Customer cmr = findCustomerById(id);
        if (cmr!=null)
        {
            cmr=customer;
            cr.save(cmr);
        }
        return cmr;
    }
    @DeleteMapping("/{id}")
    public Customer deletCustomer(@PathVariable("id")Integer id){
        Customer cmr = findCustomerById(id);
        if (cmr!=null)
        {
            cr.delete(cmr);
        }
        return cmr;
    }
    
}
