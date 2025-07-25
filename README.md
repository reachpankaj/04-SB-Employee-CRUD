Spring Boot CRUD Example with Lombok This project demonstrates a simple Spring Boot application that performs CRUD operations on an Employee entity using Lombok to reduce boilerplate code.

Project Structure:
spring-boot-crud-example
|-- src
|   |-- main
|   |   |-- java
|   |   |   |-- com
|   |   |   |   |-- example
|   |   |   |   |   |-- SpringBootCrudExampleApplication.java
|   |   |   |   |   |-- controller
|   |   |   |   |   |   |-- EmployeeController.java
|   |   |   |   |   |-- entity
|   |   |   |   |   |   |-- Employee.java
|   |   |   |   |   |-- repository
|   |   |   |   |   |   |-- EmployeeRepository.java
|   |   |   |   |   |-- service
|   |   |   |   |   |   |-- EmployeeService.java
|   |   |-- resources
|       |-- application.properties
|-- pom.xml
Step 1: Add Dependencies
Add the following dependencies to your pom.xml file:

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.28</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
Step 2: Create the Spring Boot Application Class
// src/main/java/com/example/SpringBootCrudExampleApplication.java

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudExampleApplication.class, args);
    }
}
Step 3: Create the Employee Entity
// src/main/java/com/example/entity/Employee.java

package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
}
Step 4: Create the Employee Repository
// src/main/java/com/example/repository/EmployeeRepository.java

package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
Step 5: Create the Employee Service
// src/main/java/com/example/service/EmployeeService.java

package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setRole(employeeDetails.getRole());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
Step 6: Create the Employee Controller
// src/main/java/com/example/controller/EmployeeController.java

package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
Step 7: Configure application.properties
# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


**Ensure your application.properties file is configured for your database.**
In our case we created a database in my sql CLC
here is the step to create database 
a) go to MYSQL CLC
b) enter your password to login:
C) create database tigerdb;
d) show databases;
e) finally you created you db
Example Postman Requests
Here are the Postman requests for testing the CRUD operations:

Create an Employee Method: POST
URL: http://localhost:8080/api/employees
Headers: Content-Type: application/json Body (raw, JSON):

{
  "name": "rohan rathod",
  "role": "Developer"
}
Get All Employees Method: GET
URL: http://localhost:8080/api/employees
Headers: None 4. Get an Employee by ID Method: GET

URL: http://localhost:8080/api/employees/{id}
Replace {id} with the actual employee ID. Headers: None 5. Update an Employee Method: PUT

URL: http://localhost:8080/api/employees/{id}
Replace {id} with the actual employee ID. Headers: Content-Type: application/json Body (raw, JSON):

{
  "name": "rohan rathod",
  "role": "Senior Developer"
}
Delete an Employee Method: DELETE
URL: http://localhost:8080/api/employees/{id}
Replace {id} with the actual employee ID. Headers: None These instructions will help you set up and run the Spring Boot CRUD application with Lombok. Follow the steps to create the project, and use Postman to test the endpoints.
