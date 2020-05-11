package com.haagahelia.marija.serverprogrammingproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingListProduct {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long shoppingListProductId;
    @Column(nullable= false)
    private Long shoppingListId;
    @Column(nullable= false)
    private Long productId;
    @Column(nullable= false, precision=8, scale=2)
    private float quantity;
    
    public ShoppingListProduct() {
    }

    public ShoppingListProduct(Long shoppingListId, Long productId, float quantity) {
        super();
        this.shoppingListId = shoppingListId;
        this.productId = productId;
        this.quantity = quantity;
    }
    
    public ShoppingListProduct(Long shoppingListProductId, Long shoppingListId, Long productId, float quantity) {
        super();
        this.shoppingListProductId = shoppingListProductId;
        this.shoppingListId = shoppingListId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getShoppingListProductId() {
        return shoppingListProductId;
    }
    
    public void setShoppingListProductId(Long shoppingListProductId) {
        this.shoppingListProductId = shoppingListProductId;
    }
    
    public Long getShoppingListId() {
        return shoppingListId;
    }
    
    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }
    
    public Long getProductId() {
        return productId;
    }
    
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public float getQuantity() {
        return quantity;
    }
    
    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    
    
}
