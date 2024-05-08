package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.models.venue;
import com.gp2.appwebeventsmanagementgp2.repositories.venueRepository;
import com.gp2.appwebeventsmanagementgp2.services.venueService;

@Service
public class venueServiceImpl implements venueService{

	@Autowired
	private venueRepository venueRepository;

	@Override
	public venue saveVenue(venue venue) {
		return venueRepository.save(venue);
	}

	@Override
	public List<venue> findAll() {
		return venueRepository.findAll();
	}

	@Override
	public venue getVenueById(Long id) {
		return venueRepository.findById(id).orElseThrow();
	}

	@Override
	public void updateVenue(Long id, venue updatedVenue) {
		venue existingvenue = getVenueById(id);
		existingvenue.setAddress(updatedVenue.getAddress());
		existingvenue.setLatitude(updatedVenue.getLatitude());
		existingvenue.setLongitude(updatedVenue.getLongitude());
		existingvenue.setAddress(updatedVenue.getAddress());
		existingvenue.setCity(updatedVenue.getCity());
		existingvenue.setCountry(updatedVenue.getCountry());
		existingvenue.setName(updatedVenue.getName());


		venueRepository.save(existingvenue);
	}

	@Override
	public void deleteVenue(Long id) {
		venueRepository.deleteById(id);
	}

	@Override
	public Page<venue> findAllPages(int offset, int pageSize) {
        Page<venue> venuespaged = venueRepository.findAll(PageRequest.of(offset, pageSize));
        return venuespaged;
    }
}
