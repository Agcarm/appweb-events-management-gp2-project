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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gp2.appwebeventsmanagementgp2.configurations.EnvironmentVariables;
import com.gp2.appwebeventsmanagementgp2.dto.UserDto;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.UserService;
import com.gp2.appwebeventsmanagementgp2.services.contactService;
import com.gp2.appwebeventsmanagementgp2.services.venueService;

import jakarta.validation.Valid;


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

    @Autowired
    private contactService cService;

	@GetMapping("/registration")
	public String getRegistrationPage(@ModelAttribute("user") UserDto userDto) {
		return "register";
	}

	@PostMapping("/registration")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, Model model) {
        // Password length validation
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            model.addAttribute("message", "Password cannot be empty");
            return "register"; // Return registration form with error
        } else if (userDto.getPassword().length() < 4 || userDto.getPassword().length() > 10) {
            model.addAttribute("message", "Password must be between 4 and 10 characters");
            return "register"; // Return registration form with error
        }

        try {
            userService.save(userDto);
            model.addAttribute("message", "Registered Successfully! Now Login!");
        } catch (Exception e) {
            // Handle any potential exceptions during user saving
            model.addAttribute("message", "Registration failed! Please try again.");
        }

		if (userDto.getFullname() == null || userDto.getFullname().isEmpty()) {
            model.addAttribute("message", "Fullname cannot be empty");
            return "register"; // Return registration form with error
        } else if (userDto.getFullname().length() < 2 || userDto.getFullname().length() > 100) {
            model.addAttribute("message", "Fullname must be between 2 and 100 characters");
            return "register"; // Return registration form with error
        }

        try {
            userService.save(userDto);
            model.addAttribute("message", "Registered Successfully! Now Login!");
        } catch (Exception e) {
            // Handle any potential exceptions during user saving
            model.addAttribute("message", "Registration failed! Please try again.");
        }

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
        EnvironmentVariables.setUser(userDetails);
		model.addAttribute("user", userDetails);
		model.addAttribute("eventList", eService.findAll());
		model.addAttribute("venueList", vService.listAll());
		model.addAttribute("contactList", cService.getAllcontacts());
		return "index";
	}


    @GetMapping("/managerImages/{name}")
    public String managerImages(@PathVariable("name") String name){
        return EnvironmentVariables.getEventImages()+"/"+name;
    }

}