package com.gp2.appwebeventsmanagementgp2.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.contact;

public class ActivityDto {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<contact> participants = new ArrayList<>();
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getStart() {
        return start;
    }
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    public List<contact> getParticipants() {
        return participants;
    }
    public void setParticipants(List<contact> participants) {
        this.participants.addAll(participants);
    }

    public ActivityDto(String name, LocalDateTime start, LocalDateTime end, List<contact> participants) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.participants.addAll(participants);
    }
    public ActivityDto() {
    }
    
}
