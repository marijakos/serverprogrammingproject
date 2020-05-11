package com.haagahelia.marija.serverprogrammingproject.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.haagahelia.marija.serverprogrammingproject.domain.NewProductForm;
import com.haagahelia.marija.serverprogrammingproject.domain.User;
import com.haagahelia.marija.serverprogrammingproject.domain.UserRepository;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
    //, classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class ShoppingControlerTest {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private MockMvc mockMvc;
    
    @BeforeAll
    public void setup() {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String pwd = "password";
        String hashPwd = bc.encode(pwd);
        
        User user = new User("Marija", "Kostadinov", "ShoppingControlerTestUser", hashPwd);
        repository.save(user);
        assertNotNull(user.getUserId());        
    }
    
    @Test
    @WithUserDetails("ShoppingControlerTestUser")
    public void shoppingPageOK() throws Exception {
        mockMvc.perform(get("/shopping"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Shopping List")));
    }
}
