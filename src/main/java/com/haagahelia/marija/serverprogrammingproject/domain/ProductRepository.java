package com.haagahelia.marija.serverprogrammingproject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    
    List<Product> findByUserId(Long userId);
    Product findByUserIdAndName(Long userId, String name);
}

