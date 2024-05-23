package com.gp2.appwebeventsmanagementgp2.controller;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp2.appwebeventsmanagementgp2.dto.ActivityDto;
import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.repositories.contactRepository;
import com.gp2.appwebeventsmanagementgp2.services.ActivityService;

@RestController
@RequestMapping("activityRest")
public class activityRestController {
    
    @Autowired
    ActivityService aService;
    @Autowired
    contactRepository cRepository;

    @GetMapping("/all")
    public ResponseEntity<List<activity>> getAllActivities(){
        return new ResponseEntity<>(aService.findAll(), HttpStatus.OK);
    }
 
    @PostMapping("/create")
    public ResponseEntity<activity> createActivity(@RequestBody ActivityDto activityDto) {
        activity savedActivity = aService.save(activityDto);
        return ResponseEntity.ok(savedActivity);
    }

    @PostMapping("/{id}/saveParticipants")
    public activity saveParticipants(@PathVariable("id") Long id ,@RequestBody List<contact> participants) {
        return aService.saveParticipants(id, participants);
    }
    

    @GetMapping("/{name}")
    public activity GetActivityByName(@PathVariable String name){
        return aService.findByName(name);
    }

    @GetMapping("/activity{id}")
    public activity GetActivity(@PathVariable Long id){
        return aService.findById(id);
    }

    @PostMapping("/edit/{id}")
    public activity updateActivity(@PathVariable Long id, @RequestBody ActivityDto activityDto){
        return aService.update(id, activityDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteActivity(@PathVariable("id") Long id){
        aService.delete(id);
        return "Successfully deleted";
    }
}
