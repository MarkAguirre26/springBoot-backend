package com.javarun.springboot.controller;

import com.javarun.springboot.model.SystemLog;
import com.javarun.springboot.service.SystemLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SystemLogControllerTest {

    @Mock
    private SystemLogService systemLogService;

    @InjectMocks
    private SystemLogController systemLogController;


    @Test
    public void testCreateSystemLog() {

        // Mock systemLogService.save() method behavior
        SystemLog inputLog = new SystemLog(); // Assuming you have a constructor for SystemLog
        SystemLog savedLog = new SystemLog(); // Assuming save() returns a new object or same object with updated fields
        when(systemLogService.save(inputLog)).thenReturn(savedLog);

        // Invoke the controller method
        ResponseEntity<SystemLog> responseEntity = systemLogController.createSystemLog(inputLog);

        // Assert the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedLog, responseEntity.getBody());
    }

    @Test
    public void testGetAllSystemLogs() {
        // Mock systemLogService.findAll() method behavior
        List<SystemLog> logs = new ArrayList<>(); // Assuming you have some logs in the database
        when(systemLogService.findAll()).thenReturn(logs);

        // Invoke the controller method
        ResponseEntity<List<SystemLog>> responseEntity = systemLogController.getAllSystemLogs();

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(logs, responseEntity.getBody());
    }

    @Test
    public void testGetSystemLogById_LogExists() {
        int id = 1; // ID of the log to be searched

        // Mock systemLogService.findById(id) to return a log
        SystemLog log = new SystemLog(); // Assuming you have some log with ID 1
        when(systemLogService.findById(id)).thenReturn(log);

        // Invoke the controller method
        ResponseEntity<SystemLog> responseEntity = systemLogController.getSystemLogById(id);

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(log, responseEntity.getBody());
    }

    @Test
    public void testGetSystemLogById_LogNotFound() {
        int id = 999; // ID of the log that does not exist

        // Mock systemLogService.findById(id) to return null
        when(systemLogService.findById(id)).thenReturn(null);

        // Invoke the controller method
        ResponseEntity<SystemLog> responseEntity = systemLogController.getSystemLogById(id);

        // Assert the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(null, responseEntity.getBody());
    }

}
