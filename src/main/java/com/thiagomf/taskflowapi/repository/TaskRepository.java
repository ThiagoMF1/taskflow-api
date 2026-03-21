package com.thiagomf.taskflowapi.repository;

import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.TaskStatus;
import com.thiagomf.taskflowapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    Optional<Task> findByIdAndUser(Long id, User user);
    long countByUser(User user);
    long countByUserAndStatus(User user, TaskStatus status);
}