package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gp2.appwebeventsmanagementgp2.dto.BudgetDto;
import com.gp2.appwebeventsmanagementgp2.models.budget;

public interface BudgetService {
    public budget addBudget(BudgetDto budgetDto);
  
    public List<budget> findAll();
  
    public List<budget> findAllSort(String field);
  
    public Page<budget> findAllPages(int offset, int pageSize);
  
    public budget findByIdBudget(Long Id);
  
    public budget editBudget(Long Id, BudgetDto budgetDto);
  
    public void deleteBudget(Long Id);
  
    public budget findByTitleBudget(String title);
  }
  