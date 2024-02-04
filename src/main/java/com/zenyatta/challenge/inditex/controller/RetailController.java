package com.zenyatta.challenge.inditex.controller;

import com.zenyatta.challenge.inditex.dto.CommandResponse;
import com.zenyatta.challenge.inditex.dto.CreateItemRequest;
import com.zenyatta.challenge.inditex.dto.ItemResponse;
import com.zenyatta.challenge.inditex.dto.ResponseStatus;
import com.zenyatta.challenge.inditex.dto.filter.RetailFilter;
import com.zenyatta.challenge.inditex.dto.util.ListPaginatedResponse;
import com.zenyatta.challenge.inditex.exception.RetailException;
import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.StockType;
import com.zenyatta.challenge.inditex.service.RetailService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/retail")
@RequiredArgsConstructor
public class RetailController {

    private final RetailService service;

    @PostMapping
    public CommandResponse createItem(@RequestBody @Valid final CreateItemRequest request) throws RetailException {
        final Item itemRequest = CreateItemRequest.buildItemRequest(request);
        final Item createdItem = service.createItem(itemRequest);

        return CommandResponse.buildResponse(createdItem.getId(), ResponseStatus.SUCCEEDED, "");
    }

    @GetMapping
    public ListPaginatedResponse<ItemResponse> searchItems(
            @RequestParam(required = false) final String name,
            @RequestParam(required = false) final Long salesMin,
            @RequestParam(required = false) final Long salesMax,
            @RequestParam(required = false) final Long stockMin,
            @RequestParam(required = false) final Long stockMax,
            @RequestParam(required = false) final StockType stockType,
            @RequestParam(defaultValue = "0") @Min(0) final Integer page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) final Integer pageSize) throws RetailException {
        final Pageable pageable = PageRequest.of(
                page,
                pageSize);

        final RetailFilter filter = new RetailFilter(
                name,
                salesMin,
                salesMax,
                stockMin,
                stockMax,
                stockType);

        return service.searchItems(pageable, filter);
    }

}
