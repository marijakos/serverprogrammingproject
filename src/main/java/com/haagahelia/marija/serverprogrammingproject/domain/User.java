package com.haagahelia.marija.serverprogrammingproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;    
    @Column(nullable= false,unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String passwordHash;
    @Column(nullable = false)
    private String role;    
    
    public User() {}

    public User(String username, String passwordHash) {
        this(null, null, username, passwordHash, "USER");
    }
    
    public User(String firstName, String lastName, String username, String passwordHash) {
        this(firstName, lastName, username, passwordHash, "USER");
    }
    
    public User(String firstName, String lastName, String username, String passwordHash, String role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }  
    
    public User(Long userId, String firstName, String lastName, String username, String passwordHash, String role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.userId = userId;
    }    

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }    

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + firstName + " " + lastName + ", username=" + username + "]";
    }
}
