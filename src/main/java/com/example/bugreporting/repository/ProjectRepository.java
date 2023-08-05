package com.example.bugreporting.repository;

import com.example.bugreporting.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
