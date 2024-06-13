package com.gp2.appwebeventsmanagementgp2.controller;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
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
import com.gp2.appwebeventsmanagementgp2.models.User;
import com.gp2.appwebeventsmanagementgp2.models.expense;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.models.budget;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.ExpenseService;
import com.gp2.appwebeventsmanagementgp2.services.UserService;
import com.gp2.appwebeventsmanagementgp2.services.contactService;
import com.gp2.appwebeventsmanagementgp2.services.typeService;
import com.gp2.appwebeventsmanagementgp2.services.venueService;
import com.gp2.appwebeventsmanagementgp2.services.TaskService;
import com.gp2.appwebeventsmanagementgp2.services.BudgetService;

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
    typeService tService;

    @Autowired
    TaskService taService;

    @Autowired
    ExpenseService expenseService;

    @Autowired
    BudgetService budgetService;

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

        if (userDto.getFullname() == null || userDto.getFullname().isEmpty()) {
            model.addAttribute("message", "Fullname cannot be empty");
            return "register"; // Return registration form with error
        } else if (userDto.getFullname().length() < 2 || userDto.getFullname().length() > 100) {
            model.addAttribute("message", "Fullname must be between 2 and 100 characters");
            return "register"; // Return registration form with error
        }

        try {
            userService.save(userDto);
            EnvironmentVariables.setEmail(userDto.getEmail());
            model.addAttribute("message", "Registered Successfully! Now Login!");
        } catch (Exception e) {
            // Handle any potential exceptions during user saving
            model.addAttribute("message", "Registration failed! Please try again.");
        }

        return "register";
    }

    @GetMapping({ "/login", "" })
    public String login() {
        return "login";
    }

    @GetMapping("admin-page")
    public <taService> String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        EnvironmentVariables.setUser(userDetails);
        model.addAttribute("user", userDetails);
        model.addAttribute("eventList", eService.findAllSort("dateModified"));
        model.addAttribute("venueList", vService.findAll());

        model.addAttribute("taskList", taService.findAll());

        model.addAttribute("typeList", tService.listAll());
        model.addAttribute("contactList", cService.getAllcontacts());

        model.addAttribute("paidEventCount", eService.countPaidEvents());
        model.addAttribute("totalEvents", eService.findAll().size());

        // Get the list of expenses from the database
        List<expense> expenses = expenseService.findAll();

        List<budget> budgets = budgetService.findAll();

        // Add the budgets to the model
        model.addAttribute("budgets", budgets);

        // Calculate the total expenses
        double totalExpenses = expenses.stream()
                .mapToDouble(expense -> expense.getActualUnitPrice() * expense.getActualQty())
                .sum();

        // Add the total expenses to the model
        model.addAttribute("totalExpenses", totalExpenses);

        List<event> events = eService.findAll();

        // Add the events to the model
        model.addAttribute("events", events);

        List<task> tasks = taService.findAll();
        model.addAttribute("tasks", tasks);

        // File folder = new File("src/main/resources/static/images");
        File folder = new File(EnvironmentVariables.getEventImages());
        File[] listOfFiles = folder.listFiles();
        List<String> images = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().startsWith("isj")) {
                images.add(file.getName());
            }
        }

        model.addAttribute("images", images);
        return "index";
    }

    @GetMapping("/managerImages/{name}")
    public String managerImages(@PathVariable("name") String name) {
        return EnvironmentVariables.getEventImages() + "/" + name;
    }

}