package com.haagahelia.marija.serverprogrammingproject.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

	/* find last (current) ShoppingList for user */
    Optional<ShoppingList> findTopByUserIdOrderByShoppingListIdDesc(Long userId);
}