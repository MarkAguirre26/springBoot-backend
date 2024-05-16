package com.javarun.springboot.service;

import com.javarun.springboot.model.SystemLog;

import java.util.List;

public interface SystemLogService {
    SystemLog save(SystemLog systemLog);
    List<SystemLog> findAll();
    SystemLog findById(int id);
    void delete(SystemLog systemLog);
    List<SystemLog> findByTenant(Integer tenant);
}
