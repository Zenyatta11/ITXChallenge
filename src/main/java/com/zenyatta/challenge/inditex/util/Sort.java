package com.zenyatta.challenge.inditex.util;

import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.OrderCriteria;
import com.zenyatta.challenge.inditex.model.Stock;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Sort {

    private Long calculateStockAmount(final Set<Stock> stock) {
        return stock.stream()
                .mapToLong(Stock::getAmount)
                .sum();
    }

    private int compareItemsByCriteria(final Item item1, final Item item2, final OrderCriteria orderCriteria) {
        switch (orderCriteria) {
            case ID:
                return Long.compare(item1.getId(), item2.getId());

            case ID_INV:
                return Long.compare(item2.getId(), item1.getId());

            case NAME:
                return item1.getName().compareToIgnoreCase(item2.getName());

            case NAME_INV:
                return item2.getName().compareToIgnoreCase(item1.getName());

            case SALES:
                return Long.compare(item1.getSalesUnit(), item2.getSalesUnit());

            case SALES_INV:
                return Long.compare(item2.getSalesUnit(), item1.getSalesUnit());

            case STOCK:
                return Long.compare(calculateStockAmount(item2.getStock()), calculateStockAmount(item1.getStock()));

            case STOCK_INV:
                return Long.compare(calculateStockAmount(item1.getStock()), calculateStockAmount(item2.getStock()));

            default:
                throw new IllegalArgumentException("Unimplemented order criteria: " + orderCriteria);
        }
    }

    public Comparator<Item> getItemComparator(final Set<OrderCriteria> orderCriteria) {
        Set<OrderCriteria> order;

        if (orderCriteria == null || orderCriteria.isEmpty()) {
            order = new HashSet<>();
            order.add(OrderCriteria.ID);
        } else {
            order = orderCriteria;
        }

        return (item1, item2) -> {
            for (final OrderCriteria criteria : order) {
                final int result = compareItemsByCriteria(item1, item2, criteria);
                if (result != 0) {
                    return result;
                }
            }

            return 0;
        };
    }
}
