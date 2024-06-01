package com.gp2.appwebeventsmanagementgp2.dto;

import com.gp2.appwebeventsmanagementgp2.models.event;

public class BudgetDto {

    private Long id;
    private String title;
    private double estimatedAmount;
    private double actualAmount;
    private event budgetEvent;

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

    public event getBudgetEvent() {
        return budgetEvent;
    }

    public void setBudgetEvent(event budgetEvent) {
        this.budgetEvent = budgetEvent;
    }

    public BudgetDto(Long id, String title, double estimatedAmount, double actualAmount, event budgetEvent) {
        this.id = id;
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
        this.budgetEvent = budgetEvent;
    }

    public BudgetDto(String title, double estimatedAmount, double actualAmount, event budgetEvent) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
        this.budgetEvent = budgetEvent;
    }

    public BudgetDto(String title, double estimatedAmount, event budgetEvent) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.budgetEvent = budgetEvent;
    }

    public BudgetDto() {
    }

}
