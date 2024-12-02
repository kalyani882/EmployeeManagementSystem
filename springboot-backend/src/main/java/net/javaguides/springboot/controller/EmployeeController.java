package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")

public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
		
	} // http://localhost:8080/api/v1/employees/save

	
	// create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}  // http://localhost:8080/api/v1/employees


    // get employee by id rest api
     @GetMapping("/employees/{id}")
     public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    	 Employee employee=employeeRepository.findById(id)
    			           .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
    	 return  ResponseEntity.ok(employee);
    	 
     } // http://localhost:8080/api/v1/employees/1

     
     // update employee rest api
     @PutMapping("/employees/{id}")
     public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails) {
    	 Employee employee=employeeRepository.findById(id)
		           .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
         employee.setFirstName(employeeDetails.getFirstName());
         employee.setLastName(employeeDetails.getLastName());
         employee.setEmailId(employeeDetails.getEmailId());
         
         Employee updatedEmployee=employeeRepository.save(employee);
         return ResponseEntity.ok(updatedEmployee);
   }  // http://localhost:8080/api/v1/employees/1

      // Delete employee rest api
      @DeleteMapping("/employees/{id}")
      public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
    	  Employee employee=employeeRepository.findById(id)
		           .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
    	  
    	  employeeRepository.delete(employee);
    	  Map<String,Boolean> response=new HashMap<>();
    	  response.put("deleted", Boolean.TRUE);
    	  return ResponseEntity.ok(response);
     
      }
}  //http://localhost:8080/api/v1/employees/5



    	 










/*
   {
     "firstName":"tom",
     "lastName":"cruise",
     "emailId":"tom@gmail.com"
    }
 */
