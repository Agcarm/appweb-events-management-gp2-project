package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.contactTypeMapping;

public interface contactTypeMappingService {
    List<contact> findContactByType(String type);
    List<contactTypeMapping> findByType(String type);
    List<contactTypeMapping> findByContact(contact contact);
    void deleteMapping(contactTypeMapping cMapping);
    contactTypeMapping saveContactTypeMapping(contactTypeMapping cMapping);
}
