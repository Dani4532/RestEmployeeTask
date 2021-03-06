package com.example.restemployeetaskteszar.controller;

import com.example.restemployeetaskteszar.entity.Employee;
import com.example.restemployeetaskteszar.entity.Task;
import com.example.restemployeetaskteszar.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }



    @GetMapping("/employees/{id}")
    Employee findEmployeeById(@PathVariable String id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    @GetMapping("/employees")
    public List<Employee> getAllThatContain(@RequestParam(required = false, name = "name") String name){
        if(name == null){
            return repository.findAll();
        }

        return repository.findAll()
                .stream()
                .filter(employee -> employee.getFirstName().contains(name) || employee.getLastName().contains(name))
                .toList();
    }



    @GetMapping("/employees/{id}/hoursWorked")
    public Optional<Integer> getHouresWorked(@PathVariable String id){
        return repository.hoursWorkedByEmployee(id);
    }

    @GetMapping("/employees/{id}/tasks")
    public List<Task> getTasksFromTo(@PathVariable String id, @RequestParam(name = "from") String from, @RequestParam(name = "to") String to){
        return repository.tasksFinishedByEmployee(id, LocalDate.parse(from), LocalDate.parse(to));
    }




    @PostMapping("/employees")
    ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee saved = repository.save(employee);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(saved.getId());
        return ResponseEntity
                .created(uri)
                .body(saved);
    }
}
