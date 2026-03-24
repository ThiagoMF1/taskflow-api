package com.thiagomf.taskflowapi.repository;

import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    List<TaskHistory> findByTaskOrderByCreatedAtAsc(Task task);
}