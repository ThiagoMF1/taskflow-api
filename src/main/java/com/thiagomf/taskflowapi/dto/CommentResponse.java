package com.thiagomf.taskflowapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String content;
    private String authorName;
    private String createdAt;
}