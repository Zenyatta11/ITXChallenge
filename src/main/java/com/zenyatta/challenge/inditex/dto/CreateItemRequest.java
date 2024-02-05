package com.zenyatta.challenge.inditex.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zenyatta.challenge.inditex.json.CreateItemRequestDeserializer;
import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.Stock;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonDeserialize(using = CreateItemRequestDeserializer.class)
public class CreateItemRequest {
    private @NotNull String name;
    private @NotNull Long salesUnit;
    private Set<Stock> stock;

    public static Item buildItemRequest(final CreateItemRequest request) {
        return Item.builder()
                .name(request.name)
                .salesUnit(request.salesUnit)
                .stock(request.stock)
                .build();
    }
}
