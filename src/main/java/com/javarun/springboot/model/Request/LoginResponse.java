package com.javarun.springboot.model.Request;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    private Long cn;

    @Setter
    @Getter
    private String username;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private Integer age;

    // Constructors, getters, and setters

    public LoginResponse() {
    }

    public LoginResponse(String username, String email,  Integer age) {
        this.username = username;
        this.email = email;

        this.age = age;
    }

}
