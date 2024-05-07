package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gp2.appwebeventsmanagementgp2.dto.TaskDto;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.services.TaskService;
import com.gp2.appwebeventsmanagementgp2.services.EventService;

@RequestMapping("/task")
@Controller
public class taskController {

    @Autowired
    TaskService taskservice;

    @Autowired
    EventService eventService;

    @PostMapping("/createtask")
    public String createtask(@ModelAttribute("task") TaskDto tsk, @RequestParam("event") Long eventId) {
        event e = eventService.getEventById(eventId);
        task t = taskservice.addTask(tsk);
        List<task> list1 = Arrays.asList(t);
        e.setTasks(list1);
        eventService.saveEvent(e);
        return "redirect:/admin-page";
    }

}
