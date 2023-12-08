package com.ust.spring.responseentity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

class AlreadyExcist extends RuntimeException
{
    public AlreadyExcist(String msg)
    {
        super(msg);
    }
}

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarRepository cr;

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        if (car.getId()==null)
        {
            cr.save(car);
            return ResponseEntity.ok(car);
        }
        else
        {
            throw new AlreadyExcist("Already exist");
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Car>> retriveAllCars(){
        List<Car> cars = cr.findAll();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable("id")Integer id){
        Optional<Car> temp = cr.findById(id);
        if (temp.isPresent())
        {
            Car cars=temp.get();
            return ResponseEntity.ok().body(cars);
        }else
        {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id")Integer id,@RequestBody Car car){
        Optional<Car> temp = cr.findById(id);
        if (temp.isPresent())
        {
            Car cars=temp.get();
            cars.setName(car.getName());
            cars.setPrice(car.getPrice());
            cr.save(cars);
            return ResponseEntity.ok(cars);
        }else
        {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCar(@PathVariable("id")Integer id){
        Optional<Car> temp = cr.findById(id);
        boolean b=false;
        if (temp.isPresent())
        {
            Car car=temp.get();
            cr.delete(car);
            b=true;
            return ResponseEntity.ok().body(b);
        }else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/car")
    public ResponseEntity<Car> findbyname(@RequestParam("name") String name){
         Car car = cr.findByCarName(name);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/car/price")
    public ResponseEntity<List<Car>> findbyrange(@RequestParam("price") Double price){
        List<Car> cars= cr.findByCarInRange(price);
        return ResponseEntity.ok(cars);
    }

    
}
