package com.javarun.springboot.controller;

import com.javarun.springboot.model.SystemLog;
import com.javarun.springboot.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/systemlogs")
public class SystemLogController {

    private final SystemLogService systemLogService;

    @Autowired
    public SystemLogController(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    @PostMapping
    public ResponseEntity<SystemLog> createSystemLog(@RequestBody SystemLog systemLog) {
        SystemLog savedLog = systemLogService.save(systemLog);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SystemLog>> getAllSystemLogs() {
        List<SystemLog> logs = systemLogService.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemLog> getSystemLogById(@PathVariable("id") int id) {
        SystemLog log = systemLogService.findById(id);
        if (log != null) {
            return new ResponseEntity<>(log, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private   String getCurrentDateAsString() {
        // Create a new Date object with the current date and time
        Date currentDate = new Date();

        // Create a SimpleDateFormat object with the desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Format the current date using the SimpleDateFormat object
        String formattedDate = dateFormat.format(currentDate);

        // Return the formatted current date
        return formattedDate;
    }

    //http://localhost:8080/api/systemlogs/extract?error=ERROR&info=INFO&dateInput=2024-05-16
    @GetMapping("/extract")
    public ResponseEntity<String> getSystemLogFile(
            @RequestParam(name = "dateInput", required = true, defaultValue = "") String dateInput,
            @RequestParam(name = "info", required = false, defaultValue = "") String info,
            @RequestParam(name = "error", required = false, defaultValue = "") String error) {

        Path filePath = Paths.get("amzSystemLogs.log");




        StringBuilder logContentBuilder = new StringBuilder();


        try {
            if(dateInput.isEmpty()){
                dateInput =  getCurrentDateAsString();
            }

            List<String> lines = Files.readAllLines(filePath);


            String finalDateInput = dateInput;
            List<String> filteredLines = lines.stream()
                    .filter(line -> line.contains(finalDateInput))
                    .toList();

            if (!filteredLines.isEmpty()) {
                if (!info.isEmpty() && !error.isEmpty()) {
                    filteredLines = filteredLines.stream()
                            .filter(line -> line.contains(info) || line.contains(error))
                            .toList();
                } else if (!info.isEmpty()) {
                    filteredLines = filteredLines.stream()
                            .filter(line -> line.contains(info))
                            .toList();
                } else if (!error.isEmpty()) {
                    filteredLines = filteredLines.stream()
                            .filter(line -> line.contains(error))
                            .toList();
                }
            }

            filteredLines.forEach(line -> logContentBuilder.append(line).append("\n"));

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        return new ResponseEntity<>(logContentBuilder.toString(), HttpStatus.OK);
    }



    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<SystemLog>> getSystemLogsByTenant(@PathVariable("tenantId") Integer tenantId) {
        List<SystemLog> logs = systemLogService.findByTenant(tenantId);
        if (!logs.isEmpty()) {
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSystemLog(@PathVariable("id") int id) {
        SystemLog log = systemLogService.findById(id);
        if (log != null) {
            systemLogService.delete(log);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
