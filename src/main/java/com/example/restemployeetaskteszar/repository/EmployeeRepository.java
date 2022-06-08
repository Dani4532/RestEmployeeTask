package com.example.restemployeetaskteszar.repository;

import com.example.restemployeetaskteszar.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findEmployeesByFirstNameContaining(String name);
}
