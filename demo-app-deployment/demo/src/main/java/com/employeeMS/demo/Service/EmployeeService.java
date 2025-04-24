package com.employeeMS.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeMS.demo.Model.Employee;
import com.employeeMS.demo.Repository.EmpRepo;

@Service
public class EmployeeService {

    @Autowired
    private EmpRepo empRepo;

    public List<Employee> getEmployees() {
        return empRepo.findAll();
    }

    public void save(Employee employee) {
        empRepo.save(employee);
    }

    public void delete(int id) {
        Optional<Employee> emp = empRepo.findById(id);
        empRepo.delete(emp.get());
    }
    
}
