package com.gp2.appwebeventsmanagementgp2.controller;

import java.net.URI;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.gp2.appwebeventsmanagementgp2.dto.DayPilotEventDto;
import com.gp2.appwebeventsmanagementgp2.dto.EventDto;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.services.EventService;


@RequestMapping("/eventRest")
@RestController
public class eventRestController {
    @Autowired
    EventService eService;

    @GetMapping("/all")
    public ResponseEntity<List<event>> GetAllEvents() {
        return new ResponseEntity<>(eService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> CreateEvent(@RequestBody EventDto event) {
        event e = eService.save(event);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newEventUri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(e.getId())
        .toUri();
        responseHeaders.setLocation(newEventUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.
        CREATED);
    }

    @GetMapping("/{name}")
    public event GetEventByName(@PathVariable String name, Model model){
        model.addAttribute("event",eService.findByName(name));
        return eService.findByName(name);
    }

    @GetMapping("/event{id}")
    public event GetEvent(@PathVariable Long id, Model model){
        model.addAttribute("event", eService.getEventById(id));
        return eService.getEventById(id);
    }

    @PostMapping("/edit/{id}")
    public event postMethodName(@PathVariable("id") Long id, @ModelAttribute("event") event updatedEvent) {
        return  eService.updateEvent(id, updatedEvent);
    }
    
    @PostMapping("/delete/{id}")
    public String postMethodName(@PathVariable("id") Long id) {
        eService.deleteEvent(id);
        return "successful";
    }
    
    @GetMapping("/paged/{offset}/{pageSize}")
    public Page<event> findallPages(@PathVariable int offset, @PathVariable int pageSize) {
        return eService.findAllPages(offset, pageSize);
    }

    @GetMapping("/loadCalendar")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public Iterable<DayPilotEventDto> LoadEventsToCalendar(@RequestParam("start") LocalDateTime start, @RequestParam("end") LocalDateTime end) {
        return eService.findAllByStartDateBetween(start, end);
    }
}
