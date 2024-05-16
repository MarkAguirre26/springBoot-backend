package com.javarun.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "systemlogs")
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    private Long id;

    @Column(name = "tenant")
    private Integer tenant;

    @Column(name = "Level")
    private String level;

    @Column(name = "App")
    private String app;

    @Column(name = "Endpoint")
    private String endpoint;

    @Column(name = "Message")
    private String message;

    @Column(name = "system_aware_on")
    private Date systemAwareOn;

    @Column(name = "date_last_updated", insertable = false, updatable = false)
    private Date dateLastUpdated;

    @PrePersist
    protected void onCreate() {
        systemAwareOn = new Date();
    }

    // Constructors, getters, and setters

    public SystemLog() {
        // Default constructor
    }

    public SystemLog(Integer tenant, String level, String app, String endpoint, String message) {
        this.tenant = tenant;
        this.level = level;
        this.app = app;
        this.endpoint = endpoint;
        this.message = message;
        this.systemAwareOn = new Date(); // Set default value on creation
//        this.dateLastUpdated = new Date(); // Set default value on creation
    }

    // Getters and Setters for all fields

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTenant() {
        return tenant;
    }

    public void setTenant(Integer tenant) {
        this.tenant = tenant;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSystemAwareOn() {
        return systemAwareOn;
    }

    public void setSystemAwareOn(Date systemAwareOn) {
        this.systemAwareOn = systemAwareOn;
    }

    public Date getDateLastUpdated() {
        return dateLastUpdated;
    }

    public void setDateLastUpdated(Date dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }
}
