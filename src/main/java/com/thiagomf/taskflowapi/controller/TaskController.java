package com.thiagomf.taskflowapi.controller;

import com.thiagomf.taskflowapi.dto.CreateTaskRequest;
import com.thiagomf.taskflowapi.dto.TaskResponse;
import com.thiagomf.taskflowapi.dto.UpdateTaskRequest;
import com.thiagomf.taskflowapi.dto.UpdateTaskStatusRequest;
import com.thiagomf.taskflowapi.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request,
                                   Authentication authentication) {
        return taskService.createTask(request, authentication.getName());
    }

    @GetMapping
    public List<TaskResponse> getMyTasks(Authentication authentication) {
        return taskService.getMyTasks(authentication.getName());
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id,
                                    Authentication authentication) {
        return taskService.getTaskById(id, authentication.getName());
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,
                                   @Valid @RequestBody UpdateTaskRequest request,
                                   Authentication authentication) {
        return taskService.updateTask(id, request, authentication.getName());
    }

    @PatchMapping("/{id}/status")
    public TaskResponse updateTaskStatus(@PathVariable Long id,
                                         @Valid @RequestBody UpdateTaskStatusRequest request,
                                         Authentication authentication) {
        return taskService.updateTaskStatus(id, request, authentication.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id,
                           Authentication authentication) {
        taskService.deleteTask(id, authentication.getName());
    }
}