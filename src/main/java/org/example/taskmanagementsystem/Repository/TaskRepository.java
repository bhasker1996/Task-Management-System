package org.example.taskmanagementsystem.Repository;

import org.example.taskmanagementsystem.Constants.TaskStatus;
import org.example.taskmanagementsystem.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE (:title IS NULL OR t.title LIKE %:title%) " +
            "AND (:status IS NULL OR t.taskStatus = :status) " +
            "AND (:priority IS NULL OR t.priority = :priority) " +
            "AND (:due_date IS NULL OR t.due_date = :due_date)")
    Page<Task> findByTitleAndFilters(
            @Param("title") String title,
            @Param("status") TaskStatus status,
            @Param("priority") String priority,
            @Param("due_date") LocalDate due_date,
            Pageable pageable
            );

}
