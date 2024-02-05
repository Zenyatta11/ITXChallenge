package com.zenyatta.challenge.inditex.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zenyatta.challenge.inditex.json.ItemResponseSerializer;
import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.Stock;
import java.util.Set;

@JsonSerialize(using = ItemResponseSerializer.class)
public record ItemResponse(
        Long id,
        String name,
        Long salesUnit,
        Set<Stock> stock) {
    public static ItemResponse buildFromItem(final Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getSalesUnit(),
                item.getStock());
    }
}
