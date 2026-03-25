package com.thiagomf.taskflowapi.service;

import com.thiagomf.taskflowapi.dto.TaskHistoryResponse;
import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.TaskHistory;
import com.thiagomf.taskflowapi.entity.User;
import com.thiagomf.taskflowapi.exception.ResourceNotFoundException;
import com.thiagomf.taskflowapi.repository.TaskHistoryRepository;
import com.thiagomf.taskflowapi.repository.TaskRepository;
import com.thiagomf.taskflowapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public void createHistory(Task task, User user, String action) {
        TaskHistory history = TaskHistory.builder()
                .task(task)
                .user(user)
                .action(action)
                .createdAt(LocalDateTime.now())
                .build();

        taskHistoryRepository.save(history);
    }

    public List<TaskHistoryResponse> getTaskHistory(Long taskId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Task task = taskRepository.findByIdAndUser(taskId, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return taskHistoryRepository.findByTaskOrderByCreatedAtAsc(task)
                .stream()
                .map(history -> new TaskHistoryResponse(
                        history.getId(),
                        history.getAction(),
                        history.getUser().getName(),
                        history.getCreatedAt().toString()
                ))
                .toList();
    }
}