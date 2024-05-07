package com.gp2.appwebeventsmanagementgp2.models;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "budgets")
public class budget {
    @Id
    @GeneratedValue
    @Column(name = "budgetId")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "estimatedAmount")
    private double estimatedAmount;

    @Column(name = "actualAmount")
    private double actualAmount;

    
   
    public budget(Long id, String title, double estimatedAmount, double actualAmount) {
        this.id = id;
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
    }

    public budget(String title, double estimatedAmount, double actualAmount) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
      
    }


    public budget() {
    }
}