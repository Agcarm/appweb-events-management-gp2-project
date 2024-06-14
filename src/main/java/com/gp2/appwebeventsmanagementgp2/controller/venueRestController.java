package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<venue> getAllVenues() {
        List<venue> listvenue = service.findAll();
        return listvenue;
    }

    @PostMapping("/new")
    public venue addVenue(@RequestBody venue newVenue) {
        return service.saveVenue(newVenue);
    }

    @GetMapping("/edit/{id}")
    public venue getVenueById(@PathVariable(name = "id") long id) {
        return service.getVenueById(id);
    }

    @PostMapping("/update/{id}")
    public void editVenue(@PathVariable(name = "id") long id, @RequestBody venue venue) {
        service.updateVenue(id, venue);
    }

    @DeleteMapping("/delete/{id}")
    public void deletevenue(@PathVariable(name = "id") long id) {
        service.deleteVenue(id);
    }

    @GetMapping("/paged/{offset}/{pageSize}")
    public Page<venue> findallPages(@PathVariable int offset, @PathVariable int pageSize) {
        return service.findAllPages(offset, pageSize);
    }
}
