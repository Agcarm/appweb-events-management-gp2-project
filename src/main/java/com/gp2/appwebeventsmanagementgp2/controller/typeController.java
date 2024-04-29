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

import com.gp2.appwebeventsmanagementgp2.models.type;
import com.gp2.appwebeventsmanagementgp2.models.type;
import com.gp2.appwebeventsmanagementgp2.services.typeService;
import com.gp2.appwebeventsmanagementgp2.services.typeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class typeController {
    @Autowired
    private typeService service;

    @GetMapping("/type")
    public String viewHomePage(Model model) {
        List<type> listtype = service.listAll();
        model.addAttribute("listtype", listtype);
        System.out.print("Get / ");
        return "index";
    }
}

    /*@GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("type", new type());
        return "new";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savetype(@ModelAttribute("type") type std) {
        service.save(std);
        return "redirect:/events";
    }

    @PostMapping("/create")
    public String Createtype(@RequestBody String entity) {

        return entity;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEdittypePage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        type std = service.get(id);
        mav.addObject("venue", std);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String delettype(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/events";
    }

    @GetMapping("/events")
    public String showEventTab() {
        return "eventTab";
}
}*/
