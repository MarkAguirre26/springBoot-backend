package com.javarun.springboot.controller;

import com.javarun.springboot.model.SystemLog;
import com.javarun.springboot.service.SystemLogService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SystemLogControllerTest {

    @Mock
    private SystemLogService systemLogService;

    @InjectMocks
    private SystemLogController systemLogController;

    @Before
    public void setup() {
        // You can set up your mocks here if needed
    }

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

}
