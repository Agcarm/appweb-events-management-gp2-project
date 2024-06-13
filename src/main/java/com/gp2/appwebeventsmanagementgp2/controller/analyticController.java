package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gp2.appwebeventsmanagementgp2.models.budget;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.models.expense;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.services.BudgetService;
import com.gp2.appwebeventsmanagementgp2.services.EventService;
import com.gp2.appwebeventsmanagementgp2.services.ExpenseService;
import com.gp2.appwebeventsmanagementgp2.services.TaskService;
import com.gp2.appwebeventsmanagementgp2.services.venueService;

@Controller
public class analyticController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    BudgetService budgetService;

    @Autowired
    EventService eService;

    @Autowired
    TaskService taService;

    @Autowired
    venueService vService;

    @GetMapping("/analytics")
    public String showAnalytics(Model model) {
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

        return "/Analytics";
    }
}