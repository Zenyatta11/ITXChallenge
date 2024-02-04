package com.zenyatta.challenge.inditex.service;

import com.zenyatta.challenge.inditex.dto.ItemResponse;
import com.zenyatta.challenge.inditex.dto.filter.RetailFilter;
import com.zenyatta.challenge.inditex.dto.util.ListPaginatedResponse;
import com.zenyatta.challenge.inditex.exception.RetailException;
import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.repository.ItemRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RetailService {

    private final ItemRepository repository;

    public Item createItem(final Item item) throws RetailException {
        return save(item.toBuilder().build());
    }

    @Transactional(rollbackFor = Exception.class)
    public Item save(@NotNull final Item item) {
        return repository.save(item);
    }

    public ListPaginatedResponse<ItemResponse> searchItems(final Pageable pageable, final RetailFilter filter)
            throws RetailException {

        return new ListPaginatedResponse<>(repository
            .findAll(filter.toSpecifications(), pageable)
            .map(ItemResponse::buildFromItem));
    }
}
