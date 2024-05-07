package com.gp2.appwebeventsmanagementgp2.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.models.venue;


public class EventDto {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private venue eventVenue;
    private String description;
    private Integer estimatedAttendees;
    private Integer actualAttendees;
    private List<activity> activities;
    private List<task> tasks;
    private Date dateModified;
    private String status;
    private String type;
    private Boolean paidEvent;
    private String imageUrl;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    public venue getEventVenue() {
        return eventVenue;
    }
    public void setEventVenue(venue eventVenue) {
        this.eventVenue = eventVenue;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getEstimatedAttendees() {
        return estimatedAttendees;
    }
    public void setEstimatedAttendees(Integer estimatedAttendees) {
        this.estimatedAttendees = estimatedAttendees;
    }
    public Integer getActualAttendees() {
        return actualAttendees;
    }
    public void setActualAttendees(Integer actualAttendees) {
        this.actualAttendees = actualAttendees;
    }

    public Date getDateModified() {
        return dateModified;
    }
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Boolean getPaidEvent() {
        return paidEvent;
    }
    public void setPaidEvent(Boolean paidEvent) {
        this.paidEvent = paidEvent;
    }
    public List<activity> getActivities() {
        return activities;
    }
    public List<task> getTasks() {
        return tasks;
    }
    public void setActivities(List<activity> activities) {
        this.activities.addAll(activities);
    }
    public void setTasks(List<task> tasks) {
        this.tasks.addAll(tasks);
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public EventDto(String name, venue eventVenue, String description, Integer estimatedAttendees) {
        this.name = name;
        this.eventVenue = eventVenue;
        this.description = description;
        this.estimatedAttendees = estimatedAttendees;
    }

}
