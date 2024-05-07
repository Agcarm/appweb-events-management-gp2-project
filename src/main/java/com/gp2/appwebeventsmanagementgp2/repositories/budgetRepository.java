package com.gp2.appwebeventsmanagementgp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp2.appwebeventsmanagementgp2.models.budget;

public interface budgetRepository extends JpaRepository<budget, Long>{

    budget findByTitle(String title);

}