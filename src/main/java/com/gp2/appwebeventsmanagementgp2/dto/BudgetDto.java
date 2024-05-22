package com.gp2.appwebeventsmanagementgp2.dto;

public class BudgetDto {

    private Long id;
    private String title;
    private double estimatedAmount;
    private double actualAmount;

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

   

    public BudgetDto(Long id, String title, double estimatedAmount, double actualAmount) {
        this.id = id;
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
    }

    public BudgetDto(String title, double estimatedAmount, double actualAmount) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
    }

    public BudgetDto() {
    }

    

}
