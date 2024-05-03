package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.venue;
import com.gp2.appwebeventsmanagementgp2.services.venueService;

@RestController
@RequestMapping("/api/venues")
public class venueRestController {
    @Autowired
    private venueService service;

    @GetMapping("/")
    public List<venue> viewHomePage() {
        List<venue> listvenue = service.findAll();
        return listvenue;
    }

    @PostMapping("/new")
    public venue add(@RequestBody venue newVenue) {
        return service.saveVenue(newVenue);
    }

    @GetMapping("/edit/{id}")
    public venue showEditvenuePage(@PathVariable(name = "id") long id) {
        return service.getVenueById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletevenue(@PathVariable(name = "id") long id) {
        service.deleteVenue(id);
    }
}
