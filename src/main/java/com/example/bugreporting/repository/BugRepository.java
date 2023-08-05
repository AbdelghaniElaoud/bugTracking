package com.example.bugreporting.repository;

import com.example.bugreporting.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Long> {
    // You can define custom query methods here if needed
}
