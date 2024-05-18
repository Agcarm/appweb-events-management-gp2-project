package com.gp2.appwebeventsmanagementgp2.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.gp2.appwebeventsmanagementgp2.dto.DayPilotEventDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "event")
public class event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private Long id;

    @NotBlank
    @Column(name = "eventName", unique = true)
    private String name;

    @FutureOrPresent
    @Column(name = "startDate")
    private LocalDateTime startDate;

    @FutureOrPresent
    @Column(name = "endDate")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "idvenues", nullable = true)
    private venue eventVenue;

    @ManyToOne
    @JoinColumn(name = "idtype",nullable = true)
    private type eventType;

    @Size(min = 3, max = 500 , message = "Description must be minimum 3 characters, and maximum 500 characters long")
    @Column(nullable = false)
    private String description;

    @Value("${some.key:0}")
    @Column(nullable = false)
    private Integer estimatedAttendees;

    @Value("${some.key:0}")
    private Integer actualAttendees;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "activityId")
    @Column(nullable = true)
    private List<activity> activities;

    @ManyToMany(fetch = FetchType.EAGER)
    @Column(nullable = true)
    private List<task> tasks;

    private Date dateModified;

    @Column(nullable = true)
    private String status;


    private Boolean paidEvent;

    @Column(name = "image")
    private String imageUrl;



    public event(@NotBlank String name, venue eventVenue, type eventType,
            @Size(min = 3, max = 500, message = "Description must be minimum 3 characters, and maximum 500 characters long") String description,
            Integer estimatedAttendees) {
        this.name = name;
        this.eventVenue = eventVenue;
        this.eventType = eventType;
        this.description = description;
        this.estimatedAttendees = estimatedAttendees;
    }

    public event(Long id, String name, LocalDateTime startDate, LocalDateTime endDate, venue eventVenue,
            String description, Integer estimatedAttendees, Integer actualAttendees, List<activity> activities,
            List<task> tasks, Date dateModified, String status, type eventType, Boolean paidEvent) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventVenue = eventVenue;
        this.description = description;
        this.estimatedAttendees = estimatedAttendees;
        this.actualAttendees = actualAttendees;
        this.activities.addAll(activities);
        this.tasks.addAll(tasks);
        this.dateModified = dateModified;
        this.status = status;
        this.eventType = eventType;
        this.paidEvent = paidEvent;
    }

    public void setTasks(List<task> tasks) {
        this.tasks.addAll(tasks);
    }


    public DayPilotEventDto toDayPilotEvent() {
        DayPilotEventDto dpe = new DayPilotEventDto();
        dpe.setId(this.id);
        dpe.setStart(this.startDate);
        dpe.setEnd(this.endDate);
        dpe.setText(this.name);
        dpe.setColor(this.eventType.getColour());
        // ... set other properties
        return dpe;
    }
}
