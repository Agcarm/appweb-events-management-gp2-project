package com.gp2.appwebeventsmanagementgp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp2.appwebeventsmanagementgp2.models.activity;

public interface activityRepository extends JpaRepository<activity, Long> {

    activity findByName(String name);
    
}
