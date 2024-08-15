package org.example.taskmanagementsystem.DTOs;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.taskmanagementsystem.Constants.TaskStatus;
import org.example.taskmanagementsystem.Entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String title;
    private String description;
    private String taskStatus;
    private String priority;
    private String due_date;
    private Long user_id;
}
