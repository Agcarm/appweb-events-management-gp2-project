package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import com.gp2.appwebeventsmanagementgp2.dto.TaskDto;
import com.gp2.appwebeventsmanagementgp2.models.task;


public interface TaskService {
  public task addTask(TaskDto taskDto);
  public List<task> findAll();
  public task findByIdTask(Long Id);
  public task editTask(Long Id,TaskDto taskDto);
  public void deleteTask(Long Id);
  public task findByTitleTask(String title);
}
