package com.gp2.appwebeventsmanagementgp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.gp2.appwebeventsmanagementgp2.models.contact;

public interface contactRepository extends JpaRepository<contact, Long>{
    Optional<contact> findByName(String name);
}
