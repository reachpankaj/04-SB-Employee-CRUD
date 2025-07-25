package com.pankaj.service;

import java.util.List;

import com.pankaj.binding.Employee;

public interface EmployeeService {
	
	public String upsert(Employee employee);
	public Integer getById(Integer empId);
	public List<Employee> getAllData();
	public String deleteById(Integer empId);

}
