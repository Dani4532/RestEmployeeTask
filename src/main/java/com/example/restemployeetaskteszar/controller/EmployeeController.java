package com.example.restemployeetaskteszar.controller;

import com.example.restemployeetaskteszar.entity.Employee;
import com.example.restemployeetaskteszar.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> all(){
        return repository.findAll();
    }

    @GetMapping("/employees/{id}")
    Employee findEmployeeById(@PathVariable String id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
