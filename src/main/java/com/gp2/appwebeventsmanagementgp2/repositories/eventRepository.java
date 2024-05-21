package com.gp2.appwebeventsmanagementgp2.repositories;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gp2.appwebeventsmanagementgp2.models.event;

public interface eventRepository extends JpaRepository<event, Long>{
    event findByName(String name);
    Iterable<event> findAllByStartDateBetween(LocalDateTime start, LocalDateTime end);
    long countByPaidEventTrue();
    
}
