package com.dynamic.taskManagement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.dynamic.taskManagement.exceptions.dtos.TaskCreateDTO;

import jakarta.validation.Valid;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity createTask(@Valid TaskCreateDTO payload) {
        TaskEntity task = new TaskEntity();
        task.setTitle(payload.getTitle());
        task.setStatus(payload.getStatus());
        task.setDescription(payload.getDescription());
        return taskRepository.save(task);
    }

    public List<TaskEntity> listTasks(@RequestParam Optional<String> status) {
        List<TaskEntity> tasks;
        if (status.isPresent()) {
            tasks = taskRepository.findByStatus(status.get());
        } else {
            tasks = taskRepository.findAll();
        }
        return tasks.isEmpty() ? Collections.emptyList() : tasks;
    }

    public Optional<TaskEntity> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

}
