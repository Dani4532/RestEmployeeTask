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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


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

    @GetMapping("/employees?name=partial_name")
    public List<Employee> getAllThatContain(@PathVariable String name){
        return repository.findEmployeesByFirstNameContaining(name);
    }

    @GetMapping("/employees/{id}/houresWorked")
    public Integer getHouresWorked(@PathVariable String id){
        var found = repository.findById(id);
        var tasks = found.get().getTasks();
        var sum = 0;
        for (Task task : tasks) {
            sum += task.getHouresWorked();
        }
        return sum;
    }

    @GetMapping("/employees/{id}/tsks?from=__&to=__")
    public List<Task> getTasksFromTo(@PathVariable String id, @PathVariable String from, @PathVariable String to){
        var found = repository.findById(id);
        var tasks = found.get().getTasks();
        return tasks.stream().filter(task ->{if (task.getFinished().isBefore(LocalDate.parse(to)) && task.getFinished().isAfter(LocalDate.parse(from))){
            return true;
        }
            return false;
        }).toList();
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
