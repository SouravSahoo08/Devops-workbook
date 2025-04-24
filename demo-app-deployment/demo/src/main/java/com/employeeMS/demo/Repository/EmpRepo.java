package com.employeeMS.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employeeMS.demo.Model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer> {
    
}
