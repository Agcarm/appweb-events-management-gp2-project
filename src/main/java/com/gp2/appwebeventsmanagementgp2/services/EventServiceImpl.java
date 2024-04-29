package com.gp2.appwebeventsmanagementgp2.services;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.dto.EventDto;
import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.event;
import com.gp2.appwebeventsmanagementgp2.repositories.eventRepository;
import com.gp2.appwebeventsmanagementgp2.services.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private eventRepository eventRepository;

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
		existingevent.setActualAttendees(updatedEvent.getActualAttendees());
		existingevent.setDescription(updatedEvent.getDescription());
		existingevent.setEndDate(updatedEvent.getEndDate());
		existingevent.setEstimatedAttendees(updatedEvent.getEstimatedAttendees());
		existingevent.setEventVenue(updatedEvent.getEventVenue());
		existingevent.setStartDate(updatedEvent.getStartDate());
		existingevent.setPaidEvent(updatedEvent.getPaidEvent());
		existingevent.setStatus(updatedEvent.getStatus());
		// existingevent.setDateModified(updatedEvent.getDateModified());
		existingevent.setDateModified(Date.valueOf(LocalDate.now()));
		existingevent.setEventType(updatedEvent.getEventType());
		existingevent.setImage(updatedEvent.getImage());
		return eventRepository.save(existingevent);
	}

	@Override
	public event getEventById(Long id) {
		return eventRepository.findById(id).orElseThrow();
	}

	@Override
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

    @Override
    public event findByName(String name) {
        return eventRepository.findByName(name);
    }

	@Override
	public event save(EventDto event) {
		event e = new event(event.getName(), event.getEventVenue(), event.getEventType(), event.getDescription(), event.getEstimatedAttendees());
		e.setDateModified(Date.valueOf(LocalDate.now()));
		e.setStatus("not started");
		return eventRepository.save(e);
	}

}
