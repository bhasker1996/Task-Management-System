package org.example.taskmanagementsystem.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.taskmanagementsystem.Constants.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task extends BaseModel{

    private String title;
    private String description;
    private String priority;
    private LocalDate due_date;
    private Long userId;
    private TaskStatus taskStatus;

}
