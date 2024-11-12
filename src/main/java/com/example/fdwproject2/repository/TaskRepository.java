package com.example.fdwproject2.repository;

import com.example.fdwproject2.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.category LEFT JOIN FETCH t.tags WHERE t.id = :id")
    Optional<Task> findByIdWithRelations(@Param("id") Long id);

    @Query("SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.category LEFT JOIN FETCH t.tags")
    List<Task> findAllWithRelations();
}