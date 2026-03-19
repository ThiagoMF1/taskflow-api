package com.thiagomf.taskflowapi.controller;

import com.thiagomf.taskflowapi.dto.CreateTaskRequest;
import com.thiagomf.taskflowapi.dto.TaskResponse;
import com.thiagomf.taskflowapi.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest request,
                                   Authentication authentication) {
        return taskService.createTask(request, authentication.getName());
    }

    @GetMapping
    public List<TaskResponse> getMyTasks(Authentication authentication) {
        return taskService.getMyTasks(authentication.getName());
    }
}