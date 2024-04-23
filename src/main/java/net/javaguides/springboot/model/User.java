package net.javaguides.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cn;

    private String username;
    private String email;
    private String password;
    private Integer age;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String username, String email, String password, Integer age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    // Getters and setters

}
