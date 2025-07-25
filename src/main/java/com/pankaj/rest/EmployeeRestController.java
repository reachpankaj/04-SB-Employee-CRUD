package com.pankaj.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		
		List<Employee> employeeList = employeeService.getAllData();
		return new ResponseEntity<>(employeeList, HttpStatus.OK);
		
	}
	
	@PostMapping("/employees")
	public ResponseEntity<String> addEmployees(@RequestBody List<Employee> employees){
		String status = employeeService.saveEmployees(employees);
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

}
