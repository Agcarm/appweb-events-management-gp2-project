package com.gp2.appwebeventsmanagementgp2.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gp2.appwebeventsmanagementgp2.dto.BudgetDto;
import com.gp2.appwebeventsmanagementgp2.models.budget;
import com.gp2.appwebeventsmanagementgp2.services.BudgetService;
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

    @PostMapping("/createbudget")
    public BudgetDto createbudget(@RequestBody BudgetDto bud) {
        budgetservice.addBudget(bud);
        return bud ;
    }

    
    // @PostMapping("/createbudget")
    // public ResponseEntity<?> createbudget(budgetDto tsk) {
    //     budget t = budgetservice.addbudget(tsk);

    //     // Set the location header for the newly created resource
    //     HttpHeaders responseHeaders = new HttpHeaders();
    //     URI newEventUri = ServletUriComponentsBuilder
    //     .fromCurrentRequest()
    //     .path("/{id}")
    //     .buildAndExpand(t.getId())
    //     .toUri();
    //     responseHeaders.setLocation(newEventUri);
    //     return new ResponseEntity<>(null, responseHeaders, HttpStatus.
    //     CREATED);
    // }

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

}
