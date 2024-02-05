package com.zenyatta.challenge.inditex.repository;

import com.zenyatta.challenge.inditex.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Page<Item> findAll(Specification<Item> filter, Pageable pageable);

    Item findItemById(Long id);
}
