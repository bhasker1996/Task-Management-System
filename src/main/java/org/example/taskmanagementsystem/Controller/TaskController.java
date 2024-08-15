package org.example.taskmanagementsystem.Controller;

import org.example.taskmanagementsystem.DTOs.TaskDTO;
import org.example.taskmanagementsystem.Entity.Task;
import org.example.taskmanagementsystem.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity getAllTasks()
    {
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskByID(@PathVariable("id") Long id)
    {
        Task task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping()
    public ResponseEntity createTask(@RequestHeader("Authorization") String header ,@RequestBody TaskDTO taskDTO) {

        Task task = taskService.createTask(header, taskDTO);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity updateTask(@PathVariable("taskId") Long taskId,@RequestBody TaskDTO taskDTO)
    {
        Task task = taskService.updateTask(taskId, taskDTO);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId)
    {
        taskService.deleteTask(taskId);
        return  ResponseEntity.ok("Deleted task for the ID "+ taskId);
    }

    @GetMapping("/{title}")
    public ResponseEntity getFilteredTasks( @PathVariable("title") String title,
                                            @RequestParam(name="status",required = false) String status,
                                            @RequestParam(name="priority",required = false) String priority,
                                            @RequestParam(name="due_date", required = false) String due_date,
                                            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
                                            @RequestParam(name = "Size", required = false, defaultValue = "10") int Size)
    {
        Page<Task> filteredTasks = taskService.getFilteredTasks(title, status, priority, due_date, pageNo, Size);

        return ResponseEntity.ok(filteredTasks);
    }





}
