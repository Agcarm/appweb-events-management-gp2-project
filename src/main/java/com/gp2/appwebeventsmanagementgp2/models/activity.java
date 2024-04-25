package com.gp2.appwebeventsmanagementgp2.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "activities")
public class activity {
     @Id
    @GeneratedValue
    @Column(name = "activityId")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
    private LocalDateTime end;

    @ManyToMany
    private List<contact> participants;
}
