package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp2.appwebeventsmanagementgp2.models.type;
import com.gp2.appwebeventsmanagementgp2.repositories.typeRepository;

@Service
public class typeService {
    @Autowired
    private typeRepository repo;
    
    public List<type> listAll() {
        return repo.findAll();
    }
    
    public type save(type std) {
        return repo.save(std);
    }
    
    public type get(long id) {
        return repo.findById(id).get();
    }
    
    public void delete(long id) {
        repo.deleteById(id);
    }
    
}
