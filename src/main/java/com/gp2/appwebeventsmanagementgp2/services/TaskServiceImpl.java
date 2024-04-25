package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp2.appwebeventsmanagementgp2.dto.TaskDto;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.repositories.taskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    taskRepository taskRepo;

    @Override
    public task addTask(TaskDto taskDto) {

        task tsk = new task(taskDto.getTitle(), taskDto.getDeadline(), taskDto.getStatus(),
                taskDto.getRegistrationDate(), taskDto.getDescription());
        return taskRepo.save(tsk);
    }

    @Override
    public List<task> findAll() {
        return taskRepo.findAll();
    }

    @Override
    public task findByIdTask(Long Id) {
        return taskRepo.findById(Id).orElseThrow();
    }

    @Override
    public task editTask(Long Id, TaskDto taskDto) {
        task task = findByIdTask(Id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDeadline(taskDto.getDeadline());
        task.setStatus(taskDto.getStatus());
        task.setRegistrationDate(taskDto.getRegistrationDate());

        
        return taskRepo.save(task);    }

    @Override
    public void deleteTask(Long Id) {
        taskRepo.deleteById(Id);
    }

    @Override
    public task findByTitleTask(String title){
        return taskRepo.findByTitle(title);
    }

}
