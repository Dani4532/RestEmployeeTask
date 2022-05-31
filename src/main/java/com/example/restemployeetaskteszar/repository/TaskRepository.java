package com.example.restemployeetaskteszar.repository;

import com.example.restemployeetaskteszar.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
