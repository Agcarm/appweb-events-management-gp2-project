package com.gp2.appwebeventsmanagementgp2.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


import com.gp2.appwebeventsmanagementgp2.dto.DayPilotEventDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "event")
public class event {
    @Id
    @GeneratedValue
    @Column(name = "eventId")
    private Long id;

    @Column(name = "eventName", unique = true)
    private String name;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "idvenues",nullable = true)
    private venue eventVenue;

    @ManyToOne
    @JoinColumn(name = "idtype",nullable = true)
    private type eventType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer estimatedAttendees;

    private Integer actualAttendees;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "activityId")
    @Column(nullable = true)
    private List<activity> activities;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "eventId")
    @Column(nullable = true)
    private List<task> tasks;

    private Date dateModified;

    @Column(nullable = true)
    private String status;


    private Boolean paidEvent;

    @Column(name="image")
    private String imageUrl;

    public event(String name, venue eventVenue, type eventType, String description, Integer estimatedAttendees) {
        this.name = name;
        this.eventVenue = eventVenue;
        this.eventType = eventType;
        this.description = description;
        this.estimatedAttendees = estimatedAttendees;
    }

    public event(Long id, String name, LocalDateTime startDate, LocalDateTime endDate, venue eventVenue,
            String description, Integer estimatedAttendees, Integer actualAttendees, List<activity> activities,
            Date dateModified, String status, type eventType, Boolean paidEvent) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventVenue = eventVenue;
        this.description = description;
        this.estimatedAttendees = estimatedAttendees;
        this.actualAttendees = actualAttendees;
        this.activities = activities;
        this.dateModified = dateModified;
        this.status = status;
        this.eventType = eventType;
        this.paidEvent = paidEvent;
    }

    public event(){}

    public DayPilotEventDto toDayPilotEvent() {
        DayPilotEventDto dpe = new DayPilotEventDto();
        dpe.setId(this.id);
        dpe.setStart(this.startDate);
        dpe.setEnd(this.endDate);
        dpe.setText(this.name);
        // ... set other properties
        return dpe;
    }
}
