package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gp2.appwebeventsmanagementgp2.dto.ExpenseDto;
import com.gp2.appwebeventsmanagementgp2.models.expense;

public interface ExpenseService {
    public expense addExpense(ExpenseDto expenseDto);
  
    public List<expense> findAll();
  
    public List<expense> findAllSort(String field);
  
    public Page<expense> findAllPages(int offset, int pageSize);
  
    public expense findByIdExpense(Long Id);
  
    public expense editExpense(Long Id, ExpenseDto expenseDto);
  
    public void deleteExpense(Long Id);
  
    public expense findByTitleExpense(String title);
  }