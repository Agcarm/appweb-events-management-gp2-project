package com.gp2.appwebeventsmanagementgp2.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "budgets")
public class budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budgetId")
    private Long id;

    @NotBlank(message = "title is obligatory")
    @Column(name = "title", nullable = false)
    private String title;

    @DecimalMin(value = "0.0")
    @Column(name = "estimatedAmount")
    private double estimatedAmount;

    @DecimalMin(value = "0.0")
    @Column(name = "actualAmount")
    private double actualAmount;

    @OneToOne
    @JoinColumn(name = "event_id", referencedColumnName = "eventId")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private event budgetEvent;

    public budget(@NotBlank String title, @DecimalMin("0.0") double estimatedAmount,
            @DecimalMin("0.0") double actualAmount, event budgetEvent) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.actualAmount = actualAmount;
        this.budgetEvent = budgetEvent;
    }

    public budget(@NotBlank String title, @DecimalMin("0.0") double estimatedAmount, event budgetEvent) {
        this.title = title;
        this.estimatedAmount = estimatedAmount;
        this.budgetEvent = budgetEvent;
    }
}