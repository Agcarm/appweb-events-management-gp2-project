package com.gp2.appwebeventsmanagementgp2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gp2.appwebeventsmanagementgp2.dto.TaskDto;
import com.gp2.appwebeventsmanagementgp2.models.task;

@Repository
public interface taskRepository extends JpaRepository<task, Long> {

    task save(TaskDto taskDto);
    // You can add custom query methods here if needed

    task findByTitle(String title);
}
