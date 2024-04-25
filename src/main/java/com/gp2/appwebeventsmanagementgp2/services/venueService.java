package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp2.appwebeventsmanagementgp2.models.venue;
import com.gp2.appwebeventsmanagementgp2.repositories.venueRepository;

@Service
public class venueService {
    @Autowired
    private venueRepository repo;
    
    public List<venue> listAll() {
        return repo.findAll();
    }
    
    public venue save(venue std) {
        return repo.save(std);
    }
    
    public venue get(long id) {
        return repo.findById(id).get();
    }
    
    public void delete(long id) {
        repo.deleteById(id);
    }
    
}
