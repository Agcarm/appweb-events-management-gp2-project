package com.gp2.appwebeventsmanagementgp2.models;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @FutureOrPresent
    @Column(name = "deadline", nullable = true)
    private LocalDateTime deadline;
   
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_in_charge_id", nullable = true, referencedColumnName = "contactId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private contact contacts;

    @CreatedDate
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @NotBlank
    @Size(min = 3, max = 500 , message = "Description must be minimum 3 characters, and maximum 500 characters long")
    @Column(name = "description")
    private String description;

    // Additional constructors can be added as needed
    public task(@NotBlank String title, @FutureOrPresent LocalDateTime deadline, String status, Date registrationDate,
            @NotBlank @Size(min = 3, max = 500, message = "Description must be minimum 3 characters, and maximum 500 characters long") String description) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.registrationDate = registrationDate;
        this.description = description;
    }

    public task(@NotBlank String title, @FutureOrPresent LocalDateTime deadline, String status, contact contacts,
            Date registrationDate,
            @NotBlank @Size(min = 3, max = 500, message = "Description must be minimum 3 characters, and maximum 500 characters long") String description) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.contacts = contacts;
        this.registrationDate = registrationDate;
        this.description = description;
    }

}
