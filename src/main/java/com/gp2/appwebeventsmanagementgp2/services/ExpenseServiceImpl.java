package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.dto.ExpenseDto;
import com.gp2.appwebeventsmanagementgp2.models.expense;
import com.gp2.appwebeventsmanagementgp2.repositories.expenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    expenseRepository expenseRepo;

    @Override
    public expense addExpense(ExpenseDto expenseDto) {

        expense exp = new expense(expenseDto.getTitle(), expenseDto.getEstimatedUnitPrice(),
                expenseDto.getActualUnitPrice(), expenseDto.getEstimatedQty(), expenseDto.getActualQty(),
                expenseDto.getDate(),expenseDto.getBudget());
        return expenseRepo.save(exp);
    }

    @Override
    public List<expense> findAll() {
        return expenseRepo.findAll();
    }

    public List<expense> findAllSort(String field) {
        return expenseRepo.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<expense> findAllPages(int offset, int pageSize) {
        Page<expense> expensespaged = expenseRepo.findAll(PageRequest.of(offset, pageSize));
        return expensespaged;
    }

    @Override
    public expense findByIdExpense(Long Id) {
        return expenseRepo.findById(Id).orElseThrow();
    }

    @Override
    public expense editExpense(Long Id, ExpenseDto expenseDto) {
        expense expense = findByIdExpense(Id);
        expense.setTitle(expenseDto.getTitle());
        expense.setEstimatedUnitPrice(expenseDto.getEstimatedUnitPrice());
        expense.setActualUnitPrice(expenseDto.getActualUnitPrice());
        expense.setEstimatedQty(expenseDto.getEstimatedQty());
        expense.setActualQty(expenseDto.getActualQty());
        expense.setDate(expenseDto.getDate());
        expense.setBudget(expenseDto.getBudget());

        return expenseRepo.save(expense);
    }

    @Override
    public void deleteExpense(Long Id) {
        expenseRepo.deleteById(Id);
    }

    @Override
    public expense findByTitleExpense(String title) {
        return expenseRepo.findByTitle(title);
    }

}
