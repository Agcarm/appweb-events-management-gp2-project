package com.gp2.appwebeventsmanagementgp2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gp2.appwebeventsmanagementgp2.dto.ExpenseDto;
import com.gp2.appwebeventsmanagementgp2.services.BudgetService;
import com.gp2.appwebeventsmanagementgp2.services.ExpenseService;

@Controller
public class expenseController {

    @Autowired
    BudgetService budgetservice;

    @Autowired
    private ExpenseService eService;


    @GetMapping("/expenses")
    public String showBudget(Model model) {
        model.addAttribute("budgetList", budgetservice.findAll());
        model.addAttribute("expenseList", eService.findAll());
        return "Budget"; // Thymeleaf template name
    }

    @PostMapping("/exp/createexpense")
    public String createexpense(ExpenseDto bud, Model model) {
        eService.addExpense(bud);
        model.addAttribute("budgetList", budgetservice.findAll());
        model.addAttribute("expenseList", eService.findAll());
        return "Budget";
    }
}
