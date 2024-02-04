package com.zenyatta.challenge.inditex.repository;

import com.zenyatta.challenge.inditex.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Item, Long> {
}
