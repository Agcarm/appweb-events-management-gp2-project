package com.gp2.appwebeventsmanagementgp2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gp2.appwebeventsmanagementgp2.models.task;
import com.gp2.appwebeventsmanagementgp2.repositories.taskRepository;

// @Service
// public class taskService {
//     @Autowired
//     private taskRepository repo;
    
//     public List<task> listAll() {
//         return repo.findAll();
//     }
    
//     public void save(task std) {
//         repo.save(std);
//     }
    
//     public task get(long id) {
//         return repo.findById(id).get();
//     }
    
//     public void delete(long id) {
//         repo.deleteById(id);
//     }
    
// }

@Service
public class taskService {

    private final taskRepository taskRepository;

    @Autowired
    public taskService(taskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public task createtask(task task) {
        return taskRepository.save(task);
    }

    public task updatetask(Long id, task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public task gettask(Long id) {
        Optional<task> optionaltask = taskRepository.findById(id);
        return optionaltask.orElse(null);
    }

    public List<task> getAlltasks() {
        return taskRepository.findAll();
    }

    public void deletetask(Long id) {
        taskRepository.deleteById(id);
    }
}