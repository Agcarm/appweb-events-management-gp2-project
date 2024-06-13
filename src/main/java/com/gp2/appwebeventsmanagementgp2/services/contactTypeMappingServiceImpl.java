package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.contactType;
import com.gp2.appwebeventsmanagementgp2.models.contactTypeMapping;
import com.gp2.appwebeventsmanagementgp2.repositories.contactTypeMappingRepository;
import com.gp2.appwebeventsmanagementgp2.repositories.contactTypeRepository;

@Service
public class contactTypeMappingServiceImpl implements contactTypeMappingService{
    @Autowired
    private contactTypeRepository contactTypeRepo;

    @Autowired
    private contactTypeMappingRepository contactTypeMappingRepo;

    @Override
    @Transactional
    public List<contact> findContactByType(String type){
        List<contactType> cType = contactTypeRepo.findByType(type);
        List<contactTypeMapping> cTypeMapping = new ArrayList<>();
        List<contact> c = new ArrayList<>();

        for (contactType contactType : cType) {
            cTypeMapping.addAll(contactTypeMappingRepo.findByContactType(contactType));
        }

        for (contactTypeMapping ctm : cTypeMapping) {
            c.add(ctm.getContact());
        }
        return c;
    }

    @Override
    public List<contactTypeMapping> findByType(String type) {
        List<contactType> cType = contactTypeRepo.findByType(type);
        List<contactTypeMapping> cTypeMapping = new ArrayList<>();

        for (contactType contactType : cType) {
            cTypeMapping.addAll(contactTypeMappingRepo.findByContactType(contactType));
        }

        return cTypeMapping;
    }

    @Override
    public List<contactTypeMapping> findByContact(contact contact) {
        return contactTypeMappingRepo.findByContact(contact);
    }

    @Override
    public void deleteMapping(contactTypeMapping cMapping) {
        contactTypeMappingRepo.delete(cMapping);
    }

    @Override
    public contactTypeMapping saveContactTypeMapping(contactTypeMapping cMapping) {
        return contactTypeMappingRepo.save(cMapping);
    }
}
