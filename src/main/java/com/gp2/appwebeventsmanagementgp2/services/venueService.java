package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.gp2.appwebeventsmanagementgp2.models.venue;


public interface venueService {
    venue saveVenue(venue venue);

	List<venue> findAll();
    
	venue getVenueById(Long id);

	void updateVenue(Long id, venue updatedVenue);

	void deleteVenue(Long id);

    Page<venue> findAllPages(int offset, int pageSize);

}