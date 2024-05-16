package com.javarun.springboot.service.impl;

import com.javarun.springboot.exception.ResourceNotFoundException;
import com.javarun.springboot.model.SystemLog;
import com.javarun.springboot.repository.SystemLogRepository;
import com.javarun.springboot.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemLogServiceImpl implements SystemLogService {

    private final SystemLogRepository systemLogRepository;

    @Autowired
    public SystemLogServiceImpl(SystemLogRepository systemLogRepository) {
        this.systemLogRepository = systemLogRepository;
    }

    @Override
    public SystemLog save(SystemLog systemLog) {
        try {
            return systemLogRepository.save(systemLog);
        } catch (Exception ex) {
            // You can log the exception or handle it as needed
            throw new RuntimeException("Failed to save SystemLog: " + ex.getMessage());
        }
    }

    @Override
    public List<SystemLog> findAll() {
        try {
            return systemLogRepository.findAll();
        } catch (Exception ex) {
            // You can log the exception or handle it as needed
            throw new RuntimeException("Failed to retrieve SystemLogs: " + ex.getMessage());
        }
    }
    @Override
    public List<SystemLog> findByTenant(Integer tenant) {
        return systemLogRepository.findByTenant(tenant);

    }


    @Override
    public SystemLog findById(int id) {
        return systemLogRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(this.getClass()," findById: SystemLog", "Id", id));
    }

    @Override
    public void delete(SystemLog systemLog) {
        try {
            systemLogRepository.delete(systemLog);
        } catch (Exception ex) {
            // You can log the exception or handle it as needed
            throw new RuntimeException("Failed to delete SystemLog: " + ex.getMessage());
        }
    }
}
