package com.haagahelia.marija.serverprogrammingproject.domain;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    
    @Test
    public void createNewUser(){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pwd = "password";
        String hashPwd = bc.encode(pwd);
        
        User user = new User("Lazar", "Kostadinov", "lazarkos", hashPwd);
        repository.save(user);
        assertNotNull(user.getUserId());
    }

    @Test
    public void failToCreateSameUsername(){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pwd = "password";
        String hashPwd = bc.encode(pwd);
        
        User user = new User("Lazar", "Kostadinov", "lazarkos1", hashPwd);
        repository.save(user);
        assertNotNull(user.getUserId());

        User user1 = new User("Lazar", "Kostadinov", "lazarkos1", hashPwd);
        try {
            repository.save(user1);
        } catch (Exception e) {
        }
        assertNull(user1.getUserId());
    }
}
