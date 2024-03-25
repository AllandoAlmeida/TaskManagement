package com.dynamic.taskManagement.task;

import java.util.UUID;

import com.dynamic.taskManagement.task.dtos.TaskCreateDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "tasks")

public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String description;

    TaskEntity (TaskCreateDTO payload) {
        this.title = payload.getTitle();
        this.status = payload.getStatus();
        this.description = payload.getDescription();

    }    

}
