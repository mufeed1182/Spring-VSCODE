package com.ust.spring.onetoone;

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
@RequestMapping("/laptop")
public class LaptopController {
    @Autowired
    private LaptopRepository sr;

    @PostMapping
    public Laptop addLaptop(@RequestBody Laptop laptop){
        return sr.save(laptop);
    }
    @GetMapping
    public List<Laptop> retriveAllLaptops(){
        return sr.findAll();
    }
    @GetMapping("/{id}")
    public Laptop findByLaptopId(@PathVariable("id")Integer id){
        Optional<Laptop> temp = sr.findById(id);
        Laptop sd=null;
        if (temp.isPresent())
        {
            sd=temp.get();
        }
        return sd;
    }
    @PutMapping("/{id}")
    public Laptop updateLaptop(@PathVariable("id")Integer id,@RequestBody Laptop laptop){
        Laptop sd = findByLaptopId(id);
        if (sd!=null)
        {
            sd=laptop;
            sr.save(sd);
        }
        return sd;
    }
    @DeleteMapping("/{id}")
    public Laptop deleteLaptop(@PathVariable("id")Integer id){
        Laptop sd = findByLaptopId(id);
        if (sd!=null)
        {
            sr.delete(sd);
        }
        return sd;
    }

}

