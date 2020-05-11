package com.haagahelia.marija.serverprogrammingproject.domain;

import javax.validation.constraints.Size;

public class NewProductForm {
    @Size(min=1, max=40)
    private String productName = "";

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
