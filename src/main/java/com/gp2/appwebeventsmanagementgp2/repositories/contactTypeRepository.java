package com.gp2.appwebeventsmanagementgp2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp2.appwebeventsmanagementgp2.models.contactType;

public interface contactTypeRepository extends JpaRepository<contactType, Long>{
    List<contactType> findByType(String type);
}
