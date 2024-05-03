package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp2.appwebeventsmanagementgp2.models.venue;
import com.gp2.appwebeventsmanagementgp2.repositories.venueRepository;


public interface venueService {
    venue saveVenue(venue venue);

	List<venue> findAll();
	venue getVenueById(Long id);

	void updateVenue(Long id, venue updatedVenue);

	void deleteVenue(Long id);

}


