package com.gp2.appwebeventsmanagementgp2.dto;

import java.time.LocalDateTime;

import com.gp2.appwebeventsmanagementgp2.models.budget;

public class ExpenseDto {

    private Long id;
    private String title;
    private double estimatedUnitPrice;
    private double actualUnitPrice;
    private double estimatedQty;
    private double actualQty;
    private LocalDateTime date;
    // private budget budgets;

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

    public double getEstimatedUnitPrice() {
        return estimatedUnitPrice;
    }

    public void setEstimatedUnitPrice(double estimatedUnitPrice) {
        this.estimatedUnitPrice = estimatedUnitPrice;
    }

    public double getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setActualUnitPrice(double actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public double getEstimatedQty() {
        return estimatedQty;
    }

    public void setEstimatedQty(double estimatedQty) {
        this.estimatedQty = estimatedQty;
    }

    public double getActualQty() {
        return actualQty;
    }

    public void setActualQty(double actualQty) {
        this.actualQty = actualQty;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // public budget getBudgets() {
    //     return budgets;
    // }

    // public void setBudgets(budget budgets) {
    //     this.budgets = budgets;
    // }

    

    public ExpenseDto(Long id, String title, double estimatedUnitPrice, double actualUnitPrice, double estimatedQty,
            double actualQty, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.estimatedUnitPrice = estimatedUnitPrice;
        this.actualUnitPrice = actualUnitPrice;
        this.estimatedQty = estimatedQty;
        this.actualQty = actualQty;
        this.date = date;
        // this.budgets = budgets;
    }

    public ExpenseDto(String title, double estimatedUnitPrice, double actualUnitPrice, double estimatedQty,
            double actualQty, LocalDateTime date) {
        this.title = title;
        this.estimatedUnitPrice = estimatedUnitPrice;
        this.actualUnitPrice = actualUnitPrice;
        this.estimatedQty = estimatedQty;
        this.actualQty = actualQty;
        this.date = date;
        // this.budgets = budgets;
    }

    public ExpenseDto() {
    }

    
}
