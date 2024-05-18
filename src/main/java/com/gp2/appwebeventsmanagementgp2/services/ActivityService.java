package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import com.gp2.appwebeventsmanagementgp2.dto.ActivityDto;
import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.models.contact;

public interface ActivityService {
    public List<activity> findAll();
    public activity findById(Long activityId);
    public activity save(ActivityDto activityDto);
    public activity update(Long activityId, ActivityDto activityDto);
    public activity delete(Long activityId);

    public activity findByName(String name);
    public activity saveParticipants(Long activityId, List<contact> participants);
}
