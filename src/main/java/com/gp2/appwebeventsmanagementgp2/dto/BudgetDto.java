package com.gp2.appwebeventsmanagementgp2.dto;

import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.expense;

public class BudgetDto {

    private Long id;
    private String title;
    private double estimatedAmount;
    private double actualAmount;
    private List<expense> expenses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public List<expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<expense> expenses) {
        this.expenses = expenses;
    }

    public BudgetDto(Long id, String title, double estimatedAmount, double actualAmount, List<expense> expenses) {
        this.id = id;
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
        this.expenses = expenses;
    }

    public BudgetDto(String title, double estimatedAmount, double actualAmount, List<expense> expenses) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
        this.expenses = expenses;
    }

    public BudgetDto() {
    }

    

}
