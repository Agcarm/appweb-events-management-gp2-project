package com.gp2.appwebeventsmanagementgp2.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gp2.appwebeventsmanagementgp2.dto.ActivityDto;
import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.services.ActivityService;

@RestController
@RequestMapping("activityRest")
public class activityRestController {
    
    @Autowired
    ActivityService aService;

    @GetMapping("/all")
    public ResponseEntity<List<activity>> getAllActivities(){
        return new ResponseEntity<>(aService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateEvent(@RequestBody ActivityDto activity) {
        activity a = aService.save(activity);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newEventUri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(a.getId())
        .toUri();
        responseHeaders.setLocation(newEventUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.
        CREATED);
    }

    @GetMapping("/{name}")
    public activity GetEventByName(@PathVariable String name){
        return aService.findByName(name);
    }

    @GetMapping("/activity{id}")
    public activity GetEvent(@PathVariable Long id){
        return aService.findById(id);
    }

    @Transactional
    @PostMapping("/edit/{id}")
    public activity updateActivity(@PathVariable Long id, @RequestBody ActivityDto activityDto){
        return aService.update(id, activityDto);
    }
}
