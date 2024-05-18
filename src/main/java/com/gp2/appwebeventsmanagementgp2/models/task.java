package com.gp2.appwebeventsmanagementgp2.models;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task")
public class task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, nullable = true)
    private String title;

    @Column(name = "deadline", nullable = true)
    private LocalDateTime deadline;
   

    @Column(name = "status", nullable = true)
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_in_charge_id", nullable = true, referencedColumnName = "contactId")
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public task(String title, LocalDateTime deadline, String status, contact contacts, Date registrationDate,
            String description) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.contacts = contacts;
        this.registrationDate = registrationDate;
        this.description = description;
    }

}
