package com.example.restemployeetaskteszar.controller;

import com.example.restemployeetaskteszar.entity.Employee;
import com.example.restemployeetaskteszar.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/employees")
    ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee saved = repository.save(employee);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
