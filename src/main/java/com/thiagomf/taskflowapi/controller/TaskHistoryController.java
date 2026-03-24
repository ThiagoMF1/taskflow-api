package com.thiagomf.taskflowapi.controller;

import com.thiagomf.taskflowapi.dto.TaskHistoryResponse;
import com.thiagomf.taskflowapi.service.TaskHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/history")
@RequiredArgsConstructor
public class TaskHistoryController {

    private final TaskHistoryService taskHistoryService;

    @GetMapping
    public List<TaskHistoryResponse> getTaskHistory(@PathVariable Long taskId,
                                                    Authentication authentication) {
        return taskHistoryService.getTaskHistory(taskId, authentication.getName());
    }
}