package com.example.restemployeetaskteszar.repository;

import com.example.restemployeetaskteszar.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
