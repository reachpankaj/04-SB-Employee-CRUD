package com.pankaj.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pankaj.binding.Employee;
import com.pankaj.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee){
		String status = employeeService.upsert(employee);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

}
