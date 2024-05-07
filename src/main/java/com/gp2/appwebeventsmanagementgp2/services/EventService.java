package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.data.domain.Page;
import java.time.LocalDateTime;

import com.gp2.appwebeventsmanagementgp2.dto.DayPilotEventDto;
import com.gp2.appwebeventsmanagementgp2.dto.EventDto;
import com.gp2.appwebeventsmanagementgp2.models.event;

public interface EventService {
    void saveEvent(event event);

	event save(EventDto event); //using dto

	List<event> findAll();
	
	event getEventById(Long id);

	event updateEvent(Long id, event updatedEvent);

	void deleteEvent(Long id);

    event findByName(String name);

	Page<event> findAllPages(int offset, int pageSize);

	Iterable<DayPilotEventDto> findAllByStartDateBetween(LocalDateTime start, LocalDateTime end);

	double calculateProgression(Long Id);
}
