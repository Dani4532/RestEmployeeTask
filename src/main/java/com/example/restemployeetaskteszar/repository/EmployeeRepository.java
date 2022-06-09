package com.example.restemployeetaskteszar.repository;

import com.example.restemployeetaskteszar.entity.Employee;
import com.example.restemployeetaskteszar.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    List<Employee> findEmployeesByFirstNameContaining(String name);



    @Query("""
            select task
            from Task task
            where task.employee.id = ?1 and
            task.finished between ?2 and ?3
            """)
    List<Task> tasksFinishedByEmployee(String id, LocalDate from, LocalDate to);

    @Query("""
            select sum(task.hoursWorked)
            from Task task
            where task.employee.id = ?1
            """)
    Optional<Integer> hoursWorkedByEmployee(String id);

}
