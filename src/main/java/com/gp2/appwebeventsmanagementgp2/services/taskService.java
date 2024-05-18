package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gp2.appwebeventsmanagementgp2.dto.TaskDto;
import com.gp2.appwebeventsmanagementgp2.models.contact;
import com.gp2.appwebeventsmanagementgp2.models.task;

public interface TaskService {
  public task addTask(TaskDto taskDto);

  public List<task> findAll();

  public List<task> findAllSort(String field);

  public Page<task> findAllPages(int offset, int pageSize);

  public task findByIdTask(Long Id);

  public task editTask(Long Id, TaskDto taskDto);

  public void deleteTask(Long Id);

  public task findByTitleTask(String title);

  public List<task> findByContactTask(contact contacts);

  public task clearContact(Long taskId);
}
