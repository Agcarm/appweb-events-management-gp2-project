package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gp2.appwebeventsmanagementgp2.dto.ActivityDto;
import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.repositories.activityRepository;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    activityRepository aRepository;
    @Autowired
    contactService cservice;

    @Override
    public List<activity> findAll() {
        return aRepository.findAll();
    }

    @Override
    public activity findById(Long activityId) {
        return aRepository.findById(activityId).orElse(null);
    }

    @Transactional
    @Override
    public activity save(ActivityDto activityDto) {
        for (contact c : activityDto.getParticipants()) {
            if (cservice.getContactById(c.getContactId()) == null){
                cservice.saveContact(c);
            }
        }
        activity a = new activity(activityDto.getName(), activityDto.getStart(), activityDto.getEnd(), activityDto.getParticipants());
        return aRepository.save(a);
    }

    @Transactional
    @Override
    public activity update(Long activityId, ActivityDto activityDto) {
        activity a = aRepository.findById(activityId).orElseThrow();
        a.setName(activityDto.getName());
        a.setStart(activityDto.getStart());
        a.setEnd(activityDto.getEnd());
        a.setParticipants(activityDto.getParticipants());
        return aRepository.save(a);
    }

    @Transactional
    @Override
    public activity delete(Long activityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public activity findByName(String name) {
        return aRepository.findByName(name);
    }

    @Transactional
    @Override
    public activity saveParticipants(Long activityId, List<contact> participants) {
        activity a = aRepository.findById(activityId).orElseThrow();
        a.setParticipants(participants);
        return aRepository.save(a);
    }
    
}
