package com.thiagomf.taskflowapi.repository;

import com.thiagomf.taskflowapi.entity.Task;
import com.thiagomf.taskflowapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}