package com.thiagomf.taskflowapi.service;

import com.thiagomf.taskflowapi.dto.CommentResponse;
import com.thiagomf.taskflowapi.dto.CreateCommentRequest;
import com.thiagomf.taskflowapi.entity.Comment;
import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.User;
import com.thiagomf.taskflowapi.exception.ResourceNotFoundException;
import com.thiagomf.taskflowapi.repository.CommentRepository;
import com.thiagomf.taskflowapi.repository.TaskRepository;
import com.thiagomf.taskflowapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CommentResponse createComment(Long taskId, CreateCommentRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = getTaskByIdAndUser(taskId, user);

        Comment comment = Comment.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .task(task)
                .user(user)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return mapToResponse(savedComment);
    }

    public List<CommentResponse> getTaskComments(Long taskId, String userEmail) {
        User user = getUserByEmail(userEmail);
        Task task = getTaskByIdAndUser(taskId, user);

        return commentRepository.findByTaskOrderByCreatedAtAsc(task)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Task getTaskByIdAndUser(Long id, User user) {
        return taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getName(),
                comment.getCreatedAt().toString()
        );
    }
}