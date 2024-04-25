package com.gp2.appwebeventsmanagementgp2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task1")
public class task {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "status")
    private String status;

    // Assuming a Contact class exists and is an entity with its own @Entity annotation
    // @ManyToOne
    // @Column(name = "person_in_charge")
    // private contact personInCharge;

    @Column(name = "registration_date")
    private String registrationDate;

    @Column(name = "description")
    private String description;

    // Additional constructors can be added as needed
    public task(String title, String deadline, String status, String registrationDate, String description) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        // this.personInCharge = personInCharge;
        this.registrationDate = registrationDate;
        this.description = description;
    }
}
