package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import com.gp2.appwebeventsmanagementgp2.dto.TaskDto;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.services.TaskService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/task")
@RestController
public class taskRestController {

    @Autowired
    TaskService taskservice;

    @PostMapping("/createtask")
    public task createtask(@RequestBody TaskDto tsk) {
        return taskservice.addTask(tsk);
    }

    @GetMapping("/findall")
    public List<task> findall() {
        return taskservice.findAll();
    }

    @GetMapping("/sort/{field}")
    public List<task> findallSort(@PathVariable String field) {
        return taskservice.findAllSort(field);
    }

    @GetMapping("/paged/{offset}/{pageSize}")
    public Page<task> findallPages(@PathVariable int offset, @PathVariable int pageSize) {
        return taskservice.findAllPages(offset, pageSize);
    }

    @GetMapping("/{title}")
    public task getByName(@PathVariable String title, Model model) {
        model.addAttribute("title", taskservice.findByTitleTask(title));
        return taskservice.findByTitleTask(title);
    }

    @GetMapping("/task{Id}")
    public task getById(@PathVariable Long Id, Model model) {
        model.addAttribute("Id", taskservice.findByIdTask(Id));
        return taskservice.findByIdTask(Id);
    }

    @PostMapping("/edit/{id}")
    public task postMethodName(@PathVariable("id") Long id, @ModelAttribute("task") TaskDto editTask) {
        return taskservice.editTask(id, editTask);
    }

}
