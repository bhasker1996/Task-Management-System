package org.example.taskmanagementsystem.Services;

import org.example.taskmanagementsystem.DTOs.TaskDTO;
import org.example.taskmanagementsystem.Entity.Task;
import org.springframework.data.domain.Page;


import java.util.List;

public interface TaskService {

      List<Task> getAllTasks();

    Task createTask(String header,TaskDTO taskDTO);

    Task updateTask(Long taskId, TaskDTO taskDTO);

    void deleteTask(Long taskId);

    Page<Task> getFilteredTasks(String title, String status, String priority, String dueDate, int PageNo, int Size);

    Task getTaskByID(Long id);
}
