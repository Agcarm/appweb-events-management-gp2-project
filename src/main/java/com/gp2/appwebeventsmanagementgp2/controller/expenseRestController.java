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

import com.gp2.appwebeventsmanagementgp2.dto.ExpenseDto;
import com.gp2.appwebeventsmanagementgp2.models.expense;
import com.gp2.appwebeventsmanagementgp2.services.ExpenseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/expense")
@RestController
public class expenseRestController {

    @Autowired
    ExpenseService expenseservice;

    @PostMapping("/createexpense")
    public ExpenseDto createExpense(@RequestBody ExpenseDto bud) {
        expenseservice.addExpense(bud);
        return bud;
    }

    
    // @PostMapping("/createexpense")
    // public ResponseEntity<?> createexpense(expenseDto tsk) {
    //     expense t = expenseservice.addexpense(tsk);

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
    public List<expense> findall() {
        return expenseservice.findAll();
    }

    @GetMapping("/sort/{field}")
    public List<expense> findallSort(@PathVariable String field) {
        return expenseservice.findAllSort(field);
    }

    @GetMapping("/paged/{offset}/{pageSize}")
    public Page<expense> findallPages(@PathVariable int offset, @PathVariable int pageSize) {
        return expenseservice.findAllPages(offset, pageSize);
    }

    @GetMapping("/{title}")
    public expense getByName(@PathVariable String title, Model model) {
        model.addAttribute("title", expenseservice.findByTitleExpense(title));
        return expenseservice.findByTitleExpense(title);
    }

    @GetMapping("/expense{Id}")
    public expense getById(@PathVariable Long Id, Model model) {
        model.addAttribute("Id", expenseservice.findByIdExpense(Id));
        return expenseservice.findByIdExpense(Id);
    }

    @PostMapping("/edit/{id}")
    public expense postMethodName(@PathVariable("id") Long id, @ModelAttribute("expense") ExpenseDto editexpense) {
        return expenseservice.editExpense(id, editexpense);
    }

}
