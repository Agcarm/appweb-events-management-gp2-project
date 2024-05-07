package com.gp2.appwebeventsmanagementgp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gp2.appwebeventsmanagementgp2.models.expense;

public interface expenseRepository extends JpaRepository<expense, Long>{

    expense findByTitle(String title);

}