package com.gp2.appwebeventsmanagementgp2.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;
import java.util.stream.Collectors;


import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.dto.DayPilotEventDto;
import com.gp2.appwebeventsmanagementgp2.dto.EventDto;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.repositories.eventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private eventRepository eventRepository;
	@Autowired
	private TaskService taskService;

	@Override
	public void saveEvent(event event) {
		event.setStatus("not Started");
		event.setDateModified(Date.valueOf(LocalDate.now()));
		eventRepository.save(event);
	}

	@Override
	public List<event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public event updateEvent(Long id, event updatedEvent) {
		event existingevent = getEventById(id);
		existingevent.setName(updatedEvent.getName());
		existingevent.setActivities(updatedEvent.getActivities());
		existingevent.setTasks(updatedEvent.getTasks());
		existingevent.setActualAttendees(updatedEvent.getActualAttendees());
		existingevent.setDescription(updatedEvent.getDescription());
		existingevent.setEndDate(updatedEvent.getEndDate());
		existingevent.setEstimatedAttendees(updatedEvent.getEstimatedAttendees());
		existingevent.setEventVenue(updatedEvent.getEventVenue());
		existingevent.setStartDate(updatedEvent.getStartDate());
		existingevent.setPaidEvent(updatedEvent.getPaidEvent());
		existingevent.setStatus(updatedEvent.getStatus());
		existingevent.setDateModified(Date.valueOf(LocalDate.now()));
		existingevent.setEventType(updatedEvent.getEventType());
		existingevent.setImageUrl(updatedEvent.getImageUrl());
		return eventRepository.save(existingevent);
	}

	@Override
	public event getEventById(Long id) {
		return eventRepository.findById(id).get();
	}

	@Override
	public void deleteEvent(Long id) {
		getEventById(id).getTasks().forEach(task -> {
			taskService.deleteTask(task.getId());
		});
		eventRepository.deleteById(id);
	}

	@Override
	public event findByName(String name) {
		return eventRepository.findByName(name);
	}

	@Override
	public event save(EventDto event) {
		event e = new event(event.getName(), event.getEventVenue(), event.getEventType(), event.getDescription(),
				event.getEstimatedAttendees());
		e.setDateModified(Date.valueOf(LocalDate.now()));
		e.setStatus("not started");
		return eventRepository.save(e);
	}

	@Override
	public Page<event> findAllPages(int offset, int pageSize) {
		return eventRepository.findAll(PageRequest.of(offset, pageSize));
	}

	@Override
	public Iterable<DayPilotEventDto> findAllByStartDateBetween(LocalDateTime start, LocalDateTime end) {
		Iterable<event> MyEvents = eventRepository.findAllByStartDateBetween(start, end);
		return StreamSupport.stream(MyEvents.spliterator(), false).map(event::toDayPilotEvent).collect(Collectors.toList());
	}

	public double calculateProgression(Long Id) {
		event e = eventRepository.findById(Id).get();
		List<task> tasks = e.getTasks();
		System.out.println(tasks);
		int totalTasks = tasks.size();
		int accomplishedTasks = 0;
		
		for (task evenTask : tasks) {
			if (evenTask.getStatus().equals("Completed")) {
				accomplishedTasks++;
			}
		}

		// Calculate the progression as a percentage
		if(totalTasks==0){
			return 0;
		}
		
		double progression = (double) accomplishedTasks / totalTasks * 100;
		return progression;
	}

}
