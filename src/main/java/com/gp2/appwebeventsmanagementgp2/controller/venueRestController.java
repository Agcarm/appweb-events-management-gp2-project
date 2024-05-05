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
    public List<venue> viewHomePage() {
        List<venue> listvenue = service.listAll();
        System.out.print("Get / ");
        return listvenue;
    }

    @PostMapping("/new")
    public venue add(@RequestBody venue newVenue) {
        return service.save(newVenue);
    }

    @PostMapping("/save")
    public venue savevenue(@RequestBody venue std) {
        return service.save(std);
    }

    @PostMapping("/create")
    public String CreateVenue(@RequestBody String entity) {
        return entity;
    }

    @GetMapping("/edit/{id}")
    public venue showEditvenuePage(@PathVariable(name = "id") int id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletevenue(@PathVariable(name = "id") int id) {
        service.delete(id);
    }

    @GetMapping("/paged/{offset}/{pageSize}")
    public Page<venue> findallPages(@PathVariable int offset, @PathVariable int pageSize) {
        return service.findAllPages(offset, pageSize);
    }
}
