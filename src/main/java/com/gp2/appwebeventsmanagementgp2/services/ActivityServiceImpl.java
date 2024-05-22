package com.gp2.appwebeventsmanagementgp2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gp2.appwebeventsmanagementgp2.dto.ActivityDto;
import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.repositories.activityRepository;

@Transactional
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

    @Override
    public activity save(ActivityDto activityDto) {
        List<contact> contacts = new ArrayList<>();
        System.out.println(activityDto.getParticipants());
        for (contact c : activityDto.getParticipants()) {
            if (cservice.getContactById(c.getContactId()) == null){
               contacts.add(cservice.saveContact(c));
            }
            else{
                contacts.add(c);
            }
        }
        activity a = new activity(activityDto.getName(), activityDto.getStart(), activityDto.getEnd(), contacts);
        return aRepository.save(a);
    }

    @Override
    public activity update(Long activityId, ActivityDto activityDto) {
        activity a = aRepository.findById(activityId).orElseThrow();
        a.setName(activityDto.getName());
        a.setStart(activityDto.getStart());
        a.setEnd(activityDto.getEnd());
        List<contact> contacts = new ArrayList<>();
        for (contact c : activityDto.getParticipants()) {
            if (cservice.getContactById(c.getContactId()) == null){
               contacts.add(cservice.saveContact(c));
            }else{
                contacts.add(c);
            }
        }
        a.setParticipants(contacts);
        return aRepository.save(a);
    }

    @Override
    public void delete(Long activityId) {
        aRepository.deleteById(activityId);
    }

    @Override
    public activity findByName(String name) {
        return aRepository.findByName(name);
    }

    @Override
    public activity saveParticipants(Long activityId, List<contact> participants) {
        activity a = aRepository.findById(activityId).orElseThrow();
        List<contact> contacts = new ArrayList<>();
        for (contact c : participants) {
            if (cservice.getContactById(c.getContactId()) == null){
               contacts.add(cservice.saveContact(c));
            }else{
                contacts.add(c);
            }
        }
        a.setParticipants(contacts);
        return aRepository.save(a);
    }   
}
