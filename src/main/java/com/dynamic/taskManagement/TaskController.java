package com.dynamic.taskManagement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynamic.taskManagement.exceptions.dtos.TaskCreateDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskEntity> create(@Valid @RequestBody TaskCreateDTO payload) {
        var task = taskService.createTask(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> listTasks(@RequestParam Optional<String> status) {
        var taskStatus = taskService.listTasks(status);
        return ResponseEntity.status(HttpStatus.OK).body(taskStatus);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Optional<TaskEntity>> getTaskById(@PathVariable UUID taskId) {
        var taskById = taskService.getTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(taskById);
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
