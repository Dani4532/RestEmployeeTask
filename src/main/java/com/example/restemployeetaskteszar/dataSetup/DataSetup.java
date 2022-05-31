package com.example.restemployeetaskteszar.dataSetup;

import com.example.restemployeetaskteszar.entity.Employee;
import com.example.restemployeetaskteszar.entity.Task;
import com.example.restemployeetaskteszar.repository.EmployeeRepository;
import com.example.restemployeetaskteszar.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class DataSetup {

    @Bean
    CommandLineRunner saveEmployee(EmployeeRepository repository){
        return args -> {
            var employee = new Employee(null, "Christoph", "Schreiber", null);
          repository.save(employee);
        };
    }

    @Bean
    CommandLineRunner saveTask(TaskRepository repository){
        return args -> {
            var task = new Task(null, "do stuff with students", LocalDate.of(2019, 11, 10), 5, null);
            repository.save(task);
        };
    }
}
