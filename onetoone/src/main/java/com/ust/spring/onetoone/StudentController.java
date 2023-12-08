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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository sr;

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return sr.save(student);
    }
    @GetMapping
    public List<Student> retriveAllStudents(){
        return sr.findAll();
    }
    @GetMapping("/{id}")
    public Student findByStudentId(@PathVariable("id")Integer id){
        Optional<Student> temp = sr.findById(id);
        Student sd=null;
        if (temp.isPresent())
        {
            sd=temp.get();
        }
        return sd;
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id")Integer id,@RequestBody Student student){
        Student sd = findByStudentId(id);
        if (sd!=null)
        {
            sd=student;
            sr.save(sd);
        }
        return sd;
    }
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable("id")Integer id){
        Student sd = findByStudentId(id);
        if (sd!=null)
        {
            sr.delete(sd);
        }
        return sd;
    }

}

