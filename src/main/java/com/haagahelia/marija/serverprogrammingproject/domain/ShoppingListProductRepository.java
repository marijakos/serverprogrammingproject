package com.haagahelia.marija.serverprogrammingproject.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingListProductRepository  extends CrudRepository<ShoppingListProduct, Long> {

    List<ShoppingListProduct> findByShoppingListId(Long shoppingListId);
}
