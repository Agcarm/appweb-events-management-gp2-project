package com.gp2.appwebeventsmanagementgp2.models;

import java.sql.Date;
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
@Table(name = "task")
public class task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "status")
    private String status;

    // Assuming a Contact class exists and is an entity with its own @Entity annotation
    @ManyToMany
    @Column(name = "person_in_charge", nullable = true)
    private List<contact> personInCharge;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "description")
    private String description;

    // Additional constructors can be added as needed
    public task(String title, LocalDateTime deadline, String status, Date registrationDate, String description) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.registrationDate = registrationDate;
        this.description = description;
    }
}
