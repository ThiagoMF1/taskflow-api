package com.thiagomf.taskflowapi.service;

import com.thiagomf.taskflowapi.dto.CreateTaskRequest;
import com.thiagomf.taskflowapi.dto.TaskResponse;
import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.TaskStatus;
import com.thiagomf.taskflowapi.entity.User;
import com.thiagomf.taskflowapi.repository.TaskRepository;
import com.thiagomf.taskflowapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskResponse createTask(CreateTaskRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(TaskStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus().name(),
                savedTask.getPriority().name(),
                savedTask.getCreatedAt().toString()
        );
    }

    public List<TaskResponse> getMyTasks(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(user)
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus().name(),
                        task.getPriority().name(),
                        task.getCreatedAt().toString()
                ))
                .toList();
    }
}