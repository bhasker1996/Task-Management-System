package org.example.taskmanagementsystem.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD REST APIs for Task Resource"
)
public class TaskController {

    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Get All Tasks REST API",
            description = "Get All Tasks is used to get all the tasks from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping()
    public ResponseEntity getAllTasks()
    {
        List<Task> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @Operation(
            summary = "Get Task By Id REST API",
            description = "Get Task By Id is used to get a task based on ID from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @GetMapping("/{id}")
    public ResponseEntity getTaskByID(@PathVariable("id") Long id)
    {
        Task task = taskService.getTaskByID(id);
        return ResponseEntity.ok(task);
    }

    @Operation(
            summary = "Create Task REST API",
            description = "Create Task is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 Created"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PostMapping()
    public ResponseEntity createTask(@RequestHeader("Authorization") String header ,@RequestBody TaskDTO taskDTO) {

        Task task = taskService.createTask(header, taskDTO);
        return new ResponseEntity(task, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Task REST API",
            description = "Update Task is used to update a task in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PutMapping("/{taskId}")
    public ResponseEntity updateTask(@PathVariable("taskId") Long taskId,@RequestBody TaskDTO taskDTO)
    {
        Task task = taskService.updateTask(taskId, taskDTO);
        return ResponseEntity.ok(task);
    }

    @Operation(
            summary = "Delete Task REST API",
            description = "Delete Task is used to delete a task in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId)
    {
        taskService.deleteTask(taskId);
        return  ResponseEntity.ok("Deleted task for the ID "+ taskId);
    }

    @Operation(
            summary = "Filter Task REST API",
            description = "Filter Task is used to filter a task like by selecting a task based on title & filter based on status, priority, due_date in a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
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
