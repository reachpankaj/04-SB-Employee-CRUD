package com.pankaj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pankaj.binding.Employee;
import com.pankaj.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public String upsert(Employee employee) {
		employeeRepo.save(employee);
		return "success";
	}

	@Override
	public Integer getById(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllData() {
		
		return employeeRepo.findAll();
	}

	@Override
	public String deleteById(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveEmployees(List<Employee> employees) {
		employeeRepo.saveAll(employees);
		
		return "success";
	}
	
	

}
