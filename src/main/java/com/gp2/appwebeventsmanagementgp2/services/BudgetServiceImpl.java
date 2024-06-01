package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.dto.BudgetDto;
import com.gp2.appwebeventsmanagementgp2.models.budget;
import com.gp2.appwebeventsmanagementgp2.repositories.budgetRepository;

@Service
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    budgetRepository budgetRepo;

    @Override
    public budget addBudget(BudgetDto budgetDto) {
        System.out.println(budgetDto);
        budget bud = new budget(
            budgetDto.getTitle(),
            budgetDto.getEstimatedAmount(),
            budgetDto.getActualAmount(),
            budgetDto.getBudgetEvent()
        );
        return budgetRepo.save(bud);
    }

    @Override
    public List<budget> findAll() {
        return budgetRepo.findAll();
    }

    public List<budget> findAllSort(String field) {
        return budgetRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<budget> findAllPages(int offset, int pageSize) {
        Page<budget> budgetspaged = budgetRepo.findAll(PageRequest.of(offset, pageSize));
        return budgetspaged;
    }

    @Override
    public budget findByIdBudget(Long Id) {
        return budgetRepo.findById(Id).orElseThrow();
    }

    @Override
    public budget editBudget(Long Id, BudgetDto budgetDto) {
        budget budget = findByIdBudget(Id);
        budget.setTitle(budgetDto.getTitle());
        budget.setEstimatedAmount(budgetDto.getEstimatedAmount());
        budget.setActualAmount(budgetDto.getActualAmount());
        budget.setBudgetEvent(budgetDto.getBudgetEvent());

        return budgetRepo.save(budget);
    }

    @Override
    public void deleteBudget(Long Id) {
        System.out.println("This is the id I am working with: "+Id);
        budgetRepo.deleteById(Id);
    }

    @Override
    public budget findByTitleBudget(String title) {
        return budgetRepo.findByTitle(title);
    }

}
