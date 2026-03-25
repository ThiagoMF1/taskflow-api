package com.thiagomf.taskflowapi.service;

import com.thiagomf.taskflowapi.dto.CreateTaskRequest;
import com.thiagomf.taskflowapi.dto.TaskDashboardResponse;
import com.thiagomf.taskflowapi.dto.TaskResponse;
import com.thiagomf.taskflowapi.dto.UpdateTaskRequest;
import com.thiagomf.taskflowapi.dto.UpdateTaskStatusRequest;
import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.TaskStatus;
import com.thiagomf.taskflowapi.entity.User;
import com.thiagomf.taskflowapi.exception.BusinessException;
import com.thiagomf.taskflowapi.exception.ResourceNotFoundException;
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
    private final TaskHistoryService taskHistoryService;

    public TaskResponse createTask(CreateTaskRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(TaskStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        Task savedTask = taskRepository.save(task);

        taskHistoryService.createHistory(savedTask, user, "Task created");

        return mapToResponse(savedTask);
    }

    public List<TaskResponse> getMyTasks(String userEmail) {
        User user = getUserByEmail(userEmail);

        return taskRepository.findByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TaskResponse getTaskById(Long id, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = getTaskByIdAndUser(id, user);

        return mapToResponse(task);
    }

    public TaskResponse updateTask(Long id, UpdateTaskRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = getTaskByIdAndUser(id, user);

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());

        Task updatedTask = taskRepository.save(task);

        taskHistoryService.createHistory(updatedTask, user, "Task updated");

        return mapToResponse(updatedTask);
    }

    public TaskResponse updateTaskStatus(Long id, UpdateTaskStatusRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = getTaskByIdAndUser(id, user);

        validateStatusTransition(task.getStatus(), request.getStatus());

        task.setStatus(request.getStatus());

        Task updatedTask = taskRepository.save(task);

        taskHistoryService.createHistory(
                updatedTask,
                user,
                "Task status changed to " + updatedTask.getStatus().name()
        );

        return mapToResponse(updatedTask);
    }

    public void deleteTask(Long id, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = getTaskByIdAndUser(id, user);

        taskRepository.delete(task);
    }

    public TaskDashboardResponse getDashboard(String userEmail) {
        User user = getUserByEmail(userEmail);

        long totalTasks = taskRepository.countByUser(user);
        long pendingTasks = taskRepository.countByUserAndStatus(user, TaskStatus.PENDING);
        long inProgressTasks = taskRepository.countByUserAndStatus(user, TaskStatus.IN_PROGRESS);
        long completedTasks = taskRepository.countByUserAndStatus(user, TaskStatus.COMPLETED);

        return new TaskDashboardResponse(
                totalTasks,
                pendingTasks,
                inProgressTasks,
                completedTasks
        );
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Task getTaskByIdAndUser(Long id, User user) {
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    private void validateStatusTransition(TaskStatus currentStatus, TaskStatus newStatus) {
        if (currentStatus == newStatus) {
            throw new BusinessException("Task is already in status " + newStatus.name());
        }

        if (currentStatus == TaskStatus.PENDING && newStatus == TaskStatus.COMPLETED) {
            throw new BusinessException("Invalid status transition from PENDING to COMPLETED");
        }

        if (currentStatus == TaskStatus.COMPLETED) {
            throw new BusinessException("Completed tasks cannot change status");
        }
    }

    private TaskResponse mapToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getPriority().name(),
                task.getCreatedAt().toString()
        );
    }
}