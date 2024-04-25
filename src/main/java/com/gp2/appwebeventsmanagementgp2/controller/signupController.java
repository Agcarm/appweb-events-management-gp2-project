package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gp2.appwebeventsmanagementgp2.models.manager;

@Controller
@RequestMapping("/signup")
public class signupController {
    @GetMapping("")
	public String signView(Model model){
		model.addAttribute("sign", new manager());
    	return "signup";
	}
}
