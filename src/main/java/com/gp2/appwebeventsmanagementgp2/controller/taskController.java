package com.gp2.appwebeventsmanagementgp2.controller;

import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.repositories.taskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/task")
public class taskController {

    private final taskRepository taskRepository;

    @Autowired
    public taskController(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/task")
    public String redirectToTask() {
        // Logic to handle the request
        return "task"; // Redirect to task.html
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute task task) {
        // Save the task to the database
        taskRepository.save(task);
        return "redirect:/task/list"; // Redirect to the task list page
    }

    @GetMapping("/list")
    public String listtasks(Model model) {
        List<task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task/list"; // Assuming you have a Thymeleaf template named "list.html"
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new task());
        return "task/task"; // Assuming you have a Thymeleaf template named "form.html"
    }

    @PostMapping("/create")
    public String createtask(@ModelAttribute task task) {
        taskRepository.save(task);
        return "redirect:/tasks/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task ID"));
        model.addAttribute("task", task);
        return "task/form"; // Assuming you have a Thymeleaf template named "form.html"
    }

    @PostMapping("/edit/{id}")
    public String updatetask(@PathVariable Long id, @ModelAttribute task task) {
        task.setId(id);
        taskRepository.save(task);
        return "redirect:/tasks/list";
    }

    @GetMapping("/delete/{id}")
    public String deletetask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks/list";
    }
}
