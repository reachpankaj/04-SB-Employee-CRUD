package com.pankaj.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pankaj.binding.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
