package com.ust.spring.manytomany.controller;

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

import com.ust.spring.manytomany.entity.Product;
import com.ust.spring.manytomany.repository.ProductRepository;

class AlreadyExistException extends RuntimeException
{
    public  AlreadyExistException(String msg)
    {
        super(msg);
    }
}

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository pr;

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        if (product.getId()==null)
        {
            return pr.save(product);
        }
        Product pdt = findProductById(product.getId());
        if(pdt==null)
        {
            pr.save(product);
        }
        else{
            throw new AlreadyExistException("Already Exist");
        }
        return product;
    }
    @GetMapping
    public List<Product> retirveAllProducts(){
        return pr.findAll();
    }
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable("id")Integer id){
        Optional<Product> temp = pr.findById(id);
        Product pdt=null;
        if (temp.isPresent())
        {
            pdt=temp.get();
        }
        return pdt;
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id")Integer id, @RequestBody Product product){
        Product pdt = findProductById(id);
        if (pdt!=null)
        {
            pdt=product;
            pr.save(pdt);
        }
        return pdt;
    }
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id")Integer id){
        Product pdt = findProductById(id);
        if (pdt!=null)
        {
            pr.delete(pdt);
        }
        return pdt;
    }
    
}
