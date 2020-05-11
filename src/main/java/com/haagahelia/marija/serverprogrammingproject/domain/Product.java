package com.haagahelia.marija.serverprogrammingproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable= false)
    private Long userId;
    @Column(nullable = false)
    private String name;
    
    public Product() {}    
    
    public Product(Long userId, String name) {
        super();
        this.userId = userId;
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }  
    
    @Override
    public String toString() {
        return "Product [productId=" + productId + "userId=" + userId + ", name=" + name + "]";
    }    
}
