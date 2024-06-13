package com.gp2.appwebeventsmanagementgp2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.contactType;
import com.gp2.appwebeventsmanagementgp2.models.contactTypeMapping;

public interface contactTypeMappingRepository extends JpaRepository<contactTypeMapping, Long>{
    List<contactTypeMapping> findByContactType(contactType type);
    List<contactTypeMapping> findByContact(contact contact);
}
