package com.gp2.appwebeventsmanagementgp2.controller;
import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gp2.appwebeventsmanagementgp2.models.type;
import com.gp2.appwebeventsmanagementgp2.services.typeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/typeRest")
public class typeRestController {
    @Autowired
    private typeService typeservice;

    @GetMapping("/colours")
    public List<String> getTypeColours() {
        List<String> colours = new ArrayList<>();
        for (type t : typeservice.listAll()) {
            colours.add(t.getColour());
        }
        return colours;
    }
    
    @GetMapping("/all")
    public List<type> getTypes() {
        return typeservice.listAll();
    }
    
}
