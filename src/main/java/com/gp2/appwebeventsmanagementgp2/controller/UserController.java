package com.gp2.appwebeventsmanagementgp2.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gp2.appwebeventsmanagementgp2.dto.UserDto;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.UserService;
import com.gp2.appwebeventsmanagementgp2.services.venueService;


@Controller
public class UserController {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	EventService eService;
	@Autowired
    venueService vService;

	@Autowired
	private UserService userService;


	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}

	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserDto userDto, Model model) {
		userService.save(userDto);
		model.addAttribute("message", "Registered Successfuly! Now Login!");
		return "register";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// @GetMapping("user-page")
	// public String userPage (Model model, Principal principal) {
	// 	UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
	// 	model.addAttribute("user", userDetails);
	// 	return "user";
	// }

	@GetMapping("admin-page")
	public String adminPage (Model model, Principal principal) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
		model.addAttribute("user", userDetails);
		model.addAttribute("eventList", eService.findAll());
		model.addAttribute("venueList", vService.listAll());
		model.addAttribute("users",List.of("Carmela","Frederic","Yvan"));
		return "index";
	}

}