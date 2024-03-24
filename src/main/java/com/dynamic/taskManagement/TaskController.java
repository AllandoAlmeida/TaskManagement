package com.dynamic.taskManagement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
        TaskEntity createdTask = taskService.createTask(payload);
        return ResponseEntity.ok(createdTask);
    }

    @GetMapping
    public List<TaskEntity> listTasks(@RequestParam Optional<String> status) {
        return taskService.listTasks(status);
    }

    @GetMapping("/{taskId}")
    public Optional<TaskEntity> getTaskById(@PathVariable UUID taskId) {
        return taskService.getTaskById(taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
    }
}
