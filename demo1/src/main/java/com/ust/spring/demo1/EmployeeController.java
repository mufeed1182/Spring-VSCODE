package com.ust.spring.demo1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/main")
public class EmployeeController {
    @GetMapping
	public String home()
	{
		RestTemplate rt=new RestTemplate();
		ResponseEntity<Employee> result = rt.getForEntity("http://localhost:8383/employee/1", Employee.class);
		return "Hello worldd: "+result.getBody();
	}

}
