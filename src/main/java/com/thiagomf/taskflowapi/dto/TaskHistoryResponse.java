package com.thiagomf.taskflowapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskHistoryResponse {
    private Long id;
    private String action;
    private String authorName;
    private String createdAt;
}