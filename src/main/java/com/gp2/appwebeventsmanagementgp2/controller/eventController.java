package com.gp2.appwebeventsmanagementgp2.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.venueService;
import com.gp2.appwebeventsmanagementgp2.services.typeService;
import org.springframework.ui.Model; // Assuming you're using Spring MVC


import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class eventController {

    @Autowired
    private EventService eventService;
    @Autowired
	EventService eService;
	@Autowired
    venueService vService;
    @Autowired
    typeService tService;

    @GetMapping("/events/new")
    public String showEventForm(Model model) {
        model.addAttribute("event", new event());
        return "eventForm";
    }

    @GetMapping("/events/{id}/edit")
    public String editEventForm(@PathVariable("id") Long id, Model model) {
        event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "editEvent"; // Assuming you have a view named contact-edit for editing contacts
    }


    @PostMapping("/events")
public String createEvent(@ModelAttribute("event") event event, Model model) {
   eventService.saveEvent(event);
    return "redirect:/admin-page";
}


    @PostMapping("/event/{id}/update")
    public String updateEvent(@PathVariable("id") Long id, @ModelAttribute("event") event updatedEvent) {
        eventService.updateEvent(id, updatedEvent);
        return "redirect:/admin-page";
    }

    @GetMapping("/event/{id}/delete")
    public String deleteContact(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
        return "redirect:/admin-page"; // Redirect to the contact list page
    }

    @PostMapping("/{name}")
    public event GetEventByName(@PathVariable String name, Model model){
        model.addAttribute("event",eventService.findByName(name));
        return eventService.findByName(name);
    }

    @GetMapping("/eventTab")
    public String getEventView(Model model) {
        model.addAttribute("eventList", eService.findAll());
		model.addAttribute("venueList", vService.listAll());
        model.addAttribute("typeList", tService.listAll());
        return "eventTab";
    }



}
