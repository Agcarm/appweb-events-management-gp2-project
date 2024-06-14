package com.gp2.appwebeventsmanagementgp2.controller;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gp2.appwebeventsmanagementgp2.dto.UserDto;
import com.gp2.appwebeventsmanagementgp2.models.User;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.UserService;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/userRest")
@RestController
public class userRestController {
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> CreateUser(@RequestBody UserDto user) {
        User u= userService.save(user);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newEventUri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(u.getId())
        .toUri();
        responseHeaders.setLocation(newEventUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.
        CREATED);
    }
}
