package com.gp2.appwebeventsmanagementgp2.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activities")
public class activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActivity")
    private Long id;
    
    @NotBlank(message = "Name is mandatory")
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull(message = "StartDateandTime is mandatory")
    @FutureOrPresent(message = "StartDateandTime must be in the future")
    @Column
    private LocalDateTime start;

    @NotNull(message = "EndDateandTime is mandatory")
    @FutureOrPresent(message = "EndDateandTime must be in the future")
    @Column
    private LocalDateTime end;

    @ManyToMany(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<contact> participants = new ArrayList<>();

    public void setParticipants(List<contact> participants){
        for (contact c : participants) {
            if (!this.participants.contains(c)) {
                this.participants.add(c);
            } else {
                System.out.println(c.getName()+" is already there");
            }
        }
        // this.participants.addAll(participants);
    }

    public activity(@NotBlank(message = "Name is mandatory") String name,
            @NotNull(message = "StartDateandTime is mandatory") @FutureOrPresent(message = "StartDateandTime must be in the future") LocalDateTime start,
            @NotNull(message = "EndDateandTime is mandatory") @FutureOrPresent(message = "EndDateandTime must be in the future") LocalDateTime end,
            List<contact> participants) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.participants.addAll(participants);
    }

    public activity(@NotBlank(message = "Name is mandatory") String name,
            @NotNull(message = "StartDateandTime is mandatory") @FutureOrPresent(message = "StartDateandTime must be in the future") LocalDateTime start,
            @NotNull(message = "EndDateandTime is mandatory") @FutureOrPresent(message = "EndDateandTime must be in the future") LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

}
