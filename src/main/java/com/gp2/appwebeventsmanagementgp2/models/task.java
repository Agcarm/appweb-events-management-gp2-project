package com.gp2.appwebeventsmanagementgp2.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
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

    @Column(name = "title", unique = true, nullable = true)
    private String title;

    @Column(name = "deadline", nullable = true)
    private LocalDateTime deadline;
   

    @Column(name = "status", nullable = true)
    private String status;

    // Assuming a Contact class exists and is an entity with its own @Entity
    // annotation
    // @ManyToMany
    // @JoinTable(name = "person_in_charge", joinColumns = @JoinColumn(name =
    // "task_id"), inverseJoinColumns = @JoinColumn(name =
    // "person_in_charge_contact_id"))
    // private List<contact> personInCharge;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_in_charge_id", nullable = true)
    private contact contacts;

    @Column(name = "registration_date", nullable = false)
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

    public task() {
    }

}
