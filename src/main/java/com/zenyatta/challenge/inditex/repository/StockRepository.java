package com.zenyatta.challenge.inditex.repository;

import com.zenyatta.challenge.inditex.model.Stock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends CrudRepository<Stock, Long> {

    @Modifying
    @Query(value = "INSERT INTO stock_records (stock_item_id, type, amount) VALUES (:itemId, :type, :amount) ON CONFLICT (stock_item_id, type) DO UPDATE SET amount = :amount", nativeQuery = true)
    void saveConflictResolved(@Param("itemId") Long itemId, @Param("type") String type, @Param("amount") Long amount);
}
