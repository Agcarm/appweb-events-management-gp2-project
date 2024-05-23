package com.gp2.appwebeventsmanagementgp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import com.gp2.appwebeventsmanagementgp2.dto.BudgetDto;
import com.gp2.appwebeventsmanagementgp2.models.budget;
import com.gp2.appwebeventsmanagementgp2.services.BudgetService;
import com.gp2.appwebeventsmanagementgp2.services.ExpenseService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/budget")
@RestController
public class budgetRestController {

    @Autowired
    BudgetService budgetservice;

     @Autowired
    private ExpenseService eService;

    @GetMapping("/budget")
    public String showBudget(Model model) {
        model.addAttribute("budgetList", budgetservice.findAll());
        model.addAttribute("expenseList", eService.findAll());
        return "Budget"; // Thymeleaf template name
    }

    @PostMapping("/createbudget")
    public String createbudget(BudgetDto bud) {
        budgetservice.addBudget(bud);
        return "redirect:/admin-page";
    }

    @GetMapping("/findall")
    public List<budget> findall() {
        return budgetservice.findAll();
    }

    @GetMapping("/sort/{field}")
    public List<budget> findallSort(@PathVariable String field) {
        return budgetservice.findAllSort(field);
    }

    @GetMapping("/paged/{offset}/{pageSize}")
    public Page<budget> findallPages(@PathVariable int offset, @PathVariable int pageSize) {
        return budgetservice.findAllPages(offset, pageSize);
    }

    @GetMapping("/{title}")
    public budget getByName(@PathVariable String title, Model model) {
        model.addAttribute("title", budgetservice.findByTitleBudget(title));
        return budgetservice.findByTitleBudget(title);
    }

    @GetMapping("/budget{Id}")
    public budget getById(@PathVariable Long Id, Model model) {
        model.addAttribute("Id", budgetservice.findByIdBudget(Id));
        return budgetservice.findByIdBudget(Id);
    }

    @PostMapping("/edit/{id}")
    public budget postMethodName(@PathVariable("id") Long id, @ModelAttribute("budget") BudgetDto editbudget) {
        return budgetservice.editBudget(id, editbudget);
    }

    @GetMapping("/{Id}/delete")
    public String deleteTask(Long Id) {
        budgetservice.deleteBudget(Id);
        return "Task deleted successfully";
    }

}
