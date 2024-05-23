package com.gp2.appwebeventsmanagementgp2.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gp2.appwebeventsmanagementgp2.dto.ActivityDto;
import com.gp2.appwebeventsmanagementgp2.models.activity;
import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.repositories.activityRepository;
import com.gp2.appwebeventsmanagementgp2.repositories.contactRepository;

@Transactional
@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    activityRepository aRepository;

    @Autowired
    private contactService cService;

    @Override
    public activity save(ActivityDto activityDto) {
        /*verify if the given contacts exists else save it */
        List<contact> savedParticipants = new ArrayList<>();
        for (contact participant : activityDto.getParticipants()) {
            if (cService.getContactById(participant.getContactId()) == null) {
                savedParticipants.add(cService.saveContact(participant));
            }
            else {
                savedParticipants.add(participant);
            }
        }
        activity newActivity = new activity(
            activityDto.getName(),
            activityDto.getStart(),
            activityDto.getEnd(),
            savedParticipants
        );
        return aRepository.save(newActivity);
    }

    @Override
    public List<activity> findAll() {
        return aRepository.findAll();
    }

    @Override
    public activity findById(Long activityId) {
        return aRepository.findById(activityId).orElse(null);
    }

    @Override
    public activity update(Long activityId, ActivityDto activityDto) {
        activity a = aRepository.findById(activityId).orElseThrow();
        a.setName(activityDto.getName());
        a.setStart(activityDto.getStart());
        a.setEnd(activityDto.getEnd());

        /*verify if the updated contact exists else save it */
        List<contact> savedParticipants = new ArrayList<>();
        for (contact participant : activityDto.getParticipants()) {
            if (cService.getContactById(participant.getContactId()) == null) {
                savedParticipants.add(cService.saveContact(participant));
            }
            else {
                savedParticipants.add(participant);
            }
        }
        a.setParticipants(savedParticipants);

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
        a.setParticipants(participants);
        return aRepository.save(a);
    }
}
