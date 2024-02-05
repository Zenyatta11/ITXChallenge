package com.zenyatta.challenge.inditex.dto.filter;

import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.StockType;
import org.springframework.data.jpa.domain.Specification;

public record RetailFilter(
        String name,
        Long salesMin,
        Long salesMax,
        Long stockMin,
        Long stockMax,
        StockType stockType) {

    public Specification<Item> toSpecifications() {
        Specification<Item> specs = Specification.where(null);

        if (name != null) {
            specs = specs.and(RetailSpecification.searchByName(name));
        }

        if (salesMin != null) {
            specs = specs.and(RetailSpecification.minSales(salesMin));
        }

        if (salesMax != null) {
            specs = specs.and(RetailSpecification.maxSales(salesMax));
        }

        if (stockMin != null ||
                stockMax != null ||
                stockType != null) {
            specs = specs.and(RetailSpecification.stockInRangeAndStockType(stockMin, stockMax, stockType));
        }

        return specs;
    }
}
