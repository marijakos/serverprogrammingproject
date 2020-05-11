package com.haagahelia.marija.serverprogrammingproject.domain;

import javax.validation.constraints.Size;

public class ShoppingListForm {
    private Long id;
    
    @Size(min=0, max=50)
    private String name = "";
    
    private float quantity = 1.0f;

    public ShoppingListForm(Long id, @Size(min = 0, max = 50) String name, float quantity) {
        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
    
}
