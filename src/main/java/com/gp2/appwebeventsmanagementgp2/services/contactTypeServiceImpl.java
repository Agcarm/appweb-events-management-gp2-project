package com.gp2.appwebeventsmanagementgp2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.models.contactType;
import com.gp2.appwebeventsmanagementgp2.repositories.contactTypeRepository;

@Service
public class contactTypeServiceImpl implements contactTypeService{

    @Autowired
    contactTypeRepository cTypeRepository;
    
    @Override
    public contactType saveContactType(contactType cType) {
        return cTypeRepository.save(cType);
    }
    
}
