package com.thiagomf.taskflowapi.controller;

import com.thiagomf.taskflowapi.dto.CommentResponse;
import com.thiagomf.taskflowapi.dto.CreateCommentRequest;
import com.thiagomf.taskflowapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse createComment(@PathVariable Long taskId,
                                         @Valid @RequestBody CreateCommentRequest request,
                                         Authentication authentication) {
        return commentService.createComment(taskId, request, authentication.getName());
    }

    @GetMapping
    public List<CommentResponse> getTaskComments(@PathVariable Long taskId,
                                                 Authentication authentication) {
        return commentService.getTaskComments(taskId, authentication.getName());
    }
}