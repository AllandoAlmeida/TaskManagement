package com.dynamic.taskManagement.task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dynamic.taskManagement.exceptions.customeException.NotFoundException;
import com.dynamic.taskManagement.task.dtos.TaskCreateDTO;

import jakarta.validation.Valid;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity createTask(@Valid TaskCreateDTO payload) {
        TaskEntity task = new TaskEntity(payload);
        return taskRepository.save(task);
    }

    public List<TaskEntity> listTasks(@RequestParam Optional<String> status) {
        if (status.isPresent()) {
            return taskRepository.findByStatus(status.get());
        }
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> getTaskById(UUID id) {
        if (!taskRepository.findById(id).isPresent()) {
            throw new NotFoundException("Task Not Found");
        }
        return taskRepository.findById(id);
    }

    public void deleteTask(UUID id) {
        if (!taskRepository.findById(id).isPresent()) {
            throw new NotFoundException("Task Not Found");
        }
        taskRepository.deleteById(id);
    }

}
