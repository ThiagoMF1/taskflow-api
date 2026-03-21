package com.thiagomf.taskflowapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskDashboardResponse {
    private long totalTasks;
    private long pendingTasks;
    private long inProgressTasks;
    private long completedTasks;
}