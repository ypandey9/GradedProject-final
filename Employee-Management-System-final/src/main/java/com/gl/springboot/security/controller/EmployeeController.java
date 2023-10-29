package com.gl.springboot.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.gl.springboot.security.model.Employee;
import com.gl.springboot.security.model.Role;
import com.gl.springboot.security.model.User;
import com.gl.springboot.security.repository.EmployeeRepository;
import com.gl.springboot.security.response.ResponseMessage;
import com.gl.springboot.security.service.EmployeeService;
import com.gl.springboot.security.service.UserService;
import com.gl.springboot.security.service.impl.RoleServiceImpl;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	private EmployeeService employeeService;
	
	@Autowired
	private RoleServiceImpl roleService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	
	@PostMapping()
	public ResponseEntity<ResponseMessage> saveEmployee(@RequestBody Employee employee) {
		Employee addedEmployee=employeeService.saveEmployee(employee);
		String message="Employee added successfully !";
		ResponseMessage responseMessage = new ResponseMessage(message,addedEmployee);
		return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
	}

	
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}

	
	/*
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	*/
	
	 @PutMapping("/{id}")
	    public ResponseEntity<ResponseMessage> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
	        Employee updatedEmployee = employeeService.updateEmployee(employee, id);

	        // We can customize our response message here
	        String message = "Employee updated successfully";
	        ResponseMessage responseMessage = new ResponseMessage(message, updatedEmployee);

	        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	    }

	
	// http://localhost:8081/api/employees/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {

		// deleting employee from DB
		employeeService.deleteEmployee(id);

		return new ResponseEntity<String>("Deleted employee id  - "+id, HttpStatus.OK);
	}

	@GetMapping("/sort")
	public List<Employee> getAllEmployeeSortedByName(@RequestParam(defaultValue = "asc") String order) {
        return employeeService.getAllEmployeeSortedByName(order);
	
    }
	
	@GetMapping("/search/{keyword}")
	public List<Employee> findByKeyword(@PathVariable String keyword) {
        return employeeService.findByKeyword(keyword);
	
    }
	
	@PostMapping("/add-role")
    public ResponseEntity<ResponseMessage> saveRole(@RequestBody Role role) {
        Role addedRole = roleService.addRole(role);

        // Customize your response message here
        String message = "Role added successfully";
        ResponseMessage responseMessage = new ResponseMessage(message, addedRole);

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }
	/*
	@PostMapping("/add-role")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		return new ResponseEntity<Role>(roleService.addRole(role), HttpStatus.CREATED);
	}
	*/
	
	 @Autowired
	    private UserService userService;

	    @PostMapping("/add-user")
	    public ResponseEntity<String> addUser(@RequestBody User user) {
	        userService.save(user);
	        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
	    }

}
