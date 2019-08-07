package com.qapitol.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qapitol.employee.entity.Employee;
import com.qapitol.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping()
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> data = employeeRepository.findAll();
		return ResponseEntity.ok().body(data);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
		Employee employee = employeeRepository.findOne(id);
		if (employee != null)
			return ResponseEntity.ok().body(employee);
		else
			return ResponseEntity.notFound().build();
	}

	@PostMapping()
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer id) {
		Boolean response = true;
		try {
			employeeRepository.delete(id);
		} catch (Exception e) {
			response = false;
		}
		return ResponseEntity.accepted().body(response);
	}
}