package org.example.taskmanagementsystem.Services;

import org.example.taskmanagementsystem.Constants.TaskStatus;
import org.example.taskmanagementsystem.DTOs.TaskDTO;
import org.example.taskmanagementsystem.Entity.Task;
import org.example.taskmanagementsystem.Entity.User;
import org.example.taskmanagementsystem.Exception.TaskNotFoundException;
import org.example.taskmanagementsystem.Exception.UserNotExistsException;
import org.example.taskmanagementsystem.Repository.TaskRepository;
import org.example.taskmanagementsystem.Repository.UserRepository;
import org.example.taskmanagementsystem.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public List<Task> getAllTasks() {

        List<Task> all = taskRepository.findAll();
        return all;
    }

    @Override
    public Task createTask(String header,TaskDTO taskDTO) {

        Task task = new Task();
        task.setTaskStatus(TaskStatus.TODO);
        task.setTitle(taskDTO.getTitle());
        task.setPriority(taskDTO.getPriority());
        task.setDescription(taskDTO.getDescription());

        String username = getUsernameFromHeader(header);
        Optional<User> userOptional = userRepository.findByUsername(username);

        User user = userOptional.get();
        task.setUserId(user.getId());


        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(taskDTO.getDue_date(), df);

        task.setDue_date(date);

        Task savedtask = taskRepository.save(task);

        return savedtask;
    }

    @Override
    public Task updateTask(Long taskId, TaskDTO taskDTO) {

        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if(taskOptional.isEmpty())
        {
            throw new TaskNotFoundException("Task not created for the TaskID : "+ taskId);
        }

        Task task = taskOptional.get();

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setTaskStatus(TaskStatus.valueOf(taskDTO.getTaskStatus().toUpperCase()));
        task.setPriority(taskDTO.getPriority());

        taskRepository.save(task);

        return task;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Page<Task> getFilteredTasks(String title, String status, String priority, String date, int pageNo, int Size) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = null;

        if(date != null)
        {
            dueDate = LocalDate.parse(date, df);
        }

        Pageable pageable = PageRequest.of(pageNo, Size);

        Page<Task> page = taskRepository.findByTitleAndFilters(title, TaskStatus.valueOf(status), priority, dueDate, pageable);

        return page;

    }

    @Override
    public Task getTaskByID(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task Not found for the taskId" + id));

        return task;
    }

    public String getUsernameFromHeader(String header) {

        String bearerToken = header;
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
        {
            String token = bearerToken.substring(7, bearerToken.length());
            String username = null;
            if(StringUtils.hasText(token) && jwtTokenProvider.validatedToken(token)) {
                //get username from token

                username = jwtTokenProvider.getUsername(token);
            }
            return username;
        }
        return null;
    }


}
