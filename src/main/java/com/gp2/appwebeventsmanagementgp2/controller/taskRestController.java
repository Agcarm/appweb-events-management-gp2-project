package com.gp2.appwebeventsmanagementgp2.controller;

import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.repositories.taskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class taskRestController {

    private final taskRepository taskRepository;

    @Autowired
    public taskRestController(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public List<task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PostMapping("/save")
    public task saveTask(@RequestBody task task) {
        return taskRepository.save(task);
    }

    @PostMapping("/create")
    public task createtask(@RequestBody task task) {
        return taskRepository.save(task);
    }

    @GetMapping("/edit/{id}")
    public task getTask(@PathVariable Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
    }

    @PutMapping("/edit/{id}")
    public task updatetask(@PathVariable Long id, @RequestBody task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/delete/{id}")
    public void deletetask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
