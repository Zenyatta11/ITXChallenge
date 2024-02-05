package com.zenyatta.challenge.inditex.service;

import com.zenyatta.challenge.inditex.dto.ItemResponse;
import com.zenyatta.challenge.inditex.dto.filter.RetailFilter;
import com.zenyatta.challenge.inditex.dto.util.ListPaginatedResponse;
import com.zenyatta.challenge.inditex.dto.util.PaginationResponse;
import com.zenyatta.challenge.inditex.exception.RetailException;
import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.OrderCriteria;
import com.zenyatta.challenge.inditex.model.Stock;
import com.zenyatta.challenge.inditex.repository.ItemRepository;
import com.zenyatta.challenge.inditex.repository.StockRepository;
import com.zenyatta.challenge.inditex.util.Sort;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RetailService {

    private final ItemRepository repository;
    private final StockRepository stockRepository;

    @Transactional
    public Item createItem(final Item item) {
        final Set<Stock> stockList = item.getStock();
        final Item newItem = repository.save(item);

        for (final Stock entry : stockList) {
            stockRepository.saveConflictResolved(newItem.getId(), entry.getStockType().toString(), entry.getAmount());
        }

        return newItem;
    }

    public ListPaginatedResponse<ItemResponse> searchItems(final Pageable pageable, final RetailFilter filter,
            final Set<OrderCriteria> order)
            throws RetailException {

        final Page<Item> items = repository.findAll(filter.toSpecifications(), pageable);

        final List<ItemResponse> sortedItems = items.getContent()
                .stream()
                .sorted(new Sort().getItemComparator(order))
                .map(ItemResponse::buildFromItem)
                .collect(Collectors.toList());

        return new ListPaginatedResponse<ItemResponse>(sortedItems, PaginationResponse.fromPage(items));
    }

}
