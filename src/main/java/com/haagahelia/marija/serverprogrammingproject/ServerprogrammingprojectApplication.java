package com.haagahelia.marija.serverprogrammingproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.haagahelia.marija.serverprogrammingproject.domain.Product;
import com.haagahelia.marija.serverprogrammingproject.domain.ProductRepository;
import com.haagahelia.marija.serverprogrammingproject.domain.User;
import com.haagahelia.marija.serverprogrammingproject.domain.UserRepository;

@SpringBootApplication
public class ServerprogrammingprojectApplication {
    private static final Logger log = LoggerFactory.getLogger(ServerprogrammingprojectApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(ServerprogrammingprojectApplication.class, args);
	}

    @Bean
    public CommandLineRunner userDemo(UserRepository userRepository, ProductRepository productRepository) {
        return (args) -> {
            log.info("save a couple of users");
            
            try {
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                userRepository.save(new User("Mia", "Kostadinov", "mia", bc.encode("password")));
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                
            }
            
            log.info("fetch all users");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
                try {
                    productRepository.save(new Product(user.getUserId(), "Milk"));
                } catch (org.springframework.dao.DataIntegrityViolationException e) {
                    
                }                
                try {
                    productRepository.save(new Product(user.getUserId(), "Honey"));
                } catch (org.springframework.dao.DataIntegrityViolationException e) {
                    
                }                
            }

        };
    }
}
