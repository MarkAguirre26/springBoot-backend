package com.javarun.springboot.repository;

import com.javarun.springboot.model.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemLogRepository extends JpaRepository<SystemLog, Integer> {
    // You can add custom query methods here if needed
    List<SystemLog> findByTenant(Integer Tenant);
}
