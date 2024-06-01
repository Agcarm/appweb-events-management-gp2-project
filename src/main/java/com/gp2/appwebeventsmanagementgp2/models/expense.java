package com.gp2.appwebeventsmanagementgp2.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "expenses")
public class expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expenseId")
    private Long id;

    @NotBlank
    @Column(name = "title")
    private String title;

    @DecimalMin(value = "0.0")
    @Column(name = "estimatedUnitPrice")
    private double estimatedUnitPrice;

    @DecimalMin(value = "0.0")
    @Column(name = "actualUnitPrice")
    private double actualUnitPrice;

    @Min(1)
    @Column(name = "estimatedQty")
    private double estimatedQty;

    @Min(1)
    @Column(name = "actualQty")
    private double actualQty;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "budgetId")
    private budget budget;

    public expense(@NotBlank String title, @DecimalMin("0.0") double estimatedUnitPrice,
            @DecimalMin("0.0") double actualUnitPrice, @Min(1) double estimatedQty, @Min(1) double actualQty,
            LocalDateTime date, com.gp2.appwebeventsmanagementgp2.models.budget budget) {
        this.title = title;
        this.estimatedUnitPrice = estimatedUnitPrice;
        this.actualUnitPrice = actualUnitPrice;
        this.estimatedQty = estimatedQty;
        this.actualQty = actualQty;
        this.date = date;
        this.budget = budget;
    }
}