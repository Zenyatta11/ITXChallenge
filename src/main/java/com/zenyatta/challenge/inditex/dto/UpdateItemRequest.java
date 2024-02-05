package com.zenyatta.challenge.inditex.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zenyatta.challenge.inditex.json.UpdateItemRequestDeserializer;
import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.Stock;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonDeserialize(using = UpdateItemRequestDeserializer.class)
public class UpdateItemRequest {
    private @NotNull Long id;
    private String name;
    private Long salesUnit;
    private Set<Stock> stock;

    public static Item buildItemRequest(final UpdateItemRequest request) {
        return Item.builder()
                .id(request.id)
                .name(request.name)
                .salesUnit(request.salesUnit)
                .stock(request.stock)
                .build();
    }
}
