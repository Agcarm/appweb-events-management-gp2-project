package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


import com.gp2.appwebeventsmanagementgp2.models.venue;
import com.gp2.appwebeventsmanagementgp2.services.venueService;
import org.springframework.web.bind.annotation.PostMapping;


    @Controller
    public class venueController {

        @Autowired
        private venueService venueService;

        @GetMapping("/venue/new")
        public String showEventForm(Model model) {
            model.addAttribute("venue", new venue());
            return "venue";
        }

        @PostMapping("/venues")
        public String createVenue(@ModelAttribute("venue") venue venue) {
            venueService.saveVenue(venue);
            return "redirect:/admin-page";
        }

        @GetMapping("/venue/{id}/delete")
        public String deleteVenue(@PathVariable("id") Long id) {
            venueService.deleteVenue(id);
            return "redirect:/admin-page"; // Redirect to the contact list page
        }

        @GetMapping("/venues/{id}/edit")
        public String editContactForm(@PathVariable("id") Long id, Model model) {
           venue venue = venueService.getVenueById(id);
            model.addAttribute("venue", venue);
            return "editVenue"; // Assuming you have a view named contact-edit for editing contacts
        }

        @PostMapping("/venues/{id}/update")
        public String updateVenue(@PathVariable("id") Long id, @ModelAttribute("venue") venue updatedVenue) {
            venueService.updateVenue(id, updatedVenue);
            return "redirect:/admin-page";
        }
    }

