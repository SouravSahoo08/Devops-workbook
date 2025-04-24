package com.employeeMS.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeMS.demo.Model.Employee;
import com.employeeMS.demo.Service.EmployeeService;

@RestController
public class EmployeeController {
    
    @Autowired
    private EmployeeService empService;

    @GetMapping("/employee")
    public ResponseEntity<Object> getEmployees(){
        List<Employee> employees = empService.getEmployees();
        if(employees.size() != 0)
            return ResponseEntity.ok(employees);
        
        return ResponseEntity.badRequest().body("No employees present");
    }
    
    @PostMapping("/employee")
    public ResponseEntity<Object> saveEmployees(@RequestBody Employee employee){
        empService.save(employee);
        return ResponseEntity.ok().body("employee saved");
    }
    
    @PutMapping("/employee")
    public ResponseEntity<Object> updateEmployees(@RequestBody Employee employee){
        empService.save(employee);
        return ResponseEntity.ok().body("employee updated");
    }
    
    @DeleteMapping("/employee")
    public ResponseEntity<Object> deleteEmployees(@RequestParam("id") int id){
        empService.delete(id);
        return ResponseEntity.ok().body("employee deleted");
    }

}
