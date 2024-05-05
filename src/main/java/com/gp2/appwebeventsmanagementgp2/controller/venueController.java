package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gp2.appwebeventsmanagementgp2.models.venue;
import com.gp2.appwebeventsmanagementgp2.services.venueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class venueController {
    @Autowired
    private venueService service;

    @GetMapping("/venues")
    public String viewHomePage(Model model) {
        List<venue> listvenue = service.listAll();
        model.addAttribute("listvenue", listvenue);
        System.out.print("Get / ");
        return "index";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("venue", new venue());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savevenue(@ModelAttribute("venue") venue std) {
        service.save(std);
        return "redirect:/events";
    }

    @PostMapping("/create")
    public String CreateVenue(@RequestBody String entity) {

        return entity;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditvenuePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        venue std = service.get(id);
        mav.addObject("venue", std);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deletevenue(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/events";
    }

    @GetMapping("/events")
    public String showEventTab() {
        return "eventTab";
}

//new
@GetMapping("/addvenueform.html") // clicking on the link of add venue in the form you go to add venue page.
    public String handleLostPage() {
        return "addvenueform";
    }
}
