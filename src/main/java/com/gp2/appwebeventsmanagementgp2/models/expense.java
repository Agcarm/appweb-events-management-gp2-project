package com.gp2.appwebeventsmanagementgp2.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "expenses")
public class expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expenseId")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "estimatedUnitPrice")
    private double estimatedUnitPrice;

    @Column(name = "actualUnitPrice")
    private double actualUnitPrice;


    @Column(name = "estimatedQty")
    private double estimatedQty;
    
    @Column(name = "actualQty")
    private double actualQty;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "budgetId")
    private budget budget;


    public expense(Long id, String title, double estimatedUnitPrice, double actualUnitPrice, double estimatedQty,
            double actualQty, LocalDateTime date ,budget budget) {
        this.id = id;
        this.title = title;
        this.estimatedUnitPrice = estimatedUnitPrice;
        this.actualUnitPrice = actualUnitPrice;
        this.estimatedQty = estimatedQty;
        this.actualQty = actualQty;
        this.date = date;
        this.budget = budget;
    }

    public expense(String title, double estimatedUnitPrice, double actualUnitPrice, double estimatedQty,
            double actualQty, LocalDateTime date,budget budget) {
        this.title = title;
        this.estimatedUnitPrice = estimatedUnitPrice;
        this.actualUnitPrice = actualUnitPrice;
        this.estimatedQty = estimatedQty;
        this.actualQty = actualQty;
        this.date = date;
        this.budget = budget;
    }

    public expense() {
    }

    
}