package com.haagahelia.marija.serverprogrammingproject.domain;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long shoppingListId;
    @Column(nullable= false)
    private Long userId;
    @Column(nullable= false)
    private Date date;
    @Column(nullable= false)
    private Time time;
    
    public ShoppingList() {}

    public ShoppingList(Long userId) {
        super();
        this.userId = userId;
        long millis=System.currentTimeMillis();  
        this.date = new Date(millis);
        this.time = new Time(millis);
    }
    
    public ShoppingList(Long shoppingListId, Long userId, Date date, Time time) {
        super();
        this.shoppingListId = shoppingListId;
        this.userId = userId;
        this.date = date;
        this.time = time;
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    @Override
    public String toString() {
        return "Shopping List [userId=" + userId + ", date=" + date + " time=" + time + "]";
    }    
}
