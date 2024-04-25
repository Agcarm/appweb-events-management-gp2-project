package com.gp2.appwebeventsmanagementgp2.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.contact;

public class TaskDto {
    private Long id;
    private String title;  
    private LocalDateTime deadline;
    private String status;
    private List<contact> personInCharge;
    private Date registrationDate;
    private String description;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<contact> getPersonInCharge() {
        return personInCharge;
    }
    public void setPersonInCharge(List<contact> personInCharge) {
        this.personInCharge = personInCharge;
    }
    public Date getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public TaskDto(Long id, String title, LocalDateTime deadline, String status, List<contact> personInCharge,
            Date registrationDate, String description) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.personInCharge = personInCharge;
        this.registrationDate = registrationDate;
        this.description = description;
    }
    public TaskDto() {
    }
    public TaskDto(String title, LocalDateTime deadline, String status, Date registrationDate, String description) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.registrationDate = registrationDate;
        this.description = description;
    }

    
}
