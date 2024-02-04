package com.zenyatta.challenge.inditex.dto.filter;

import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.StockType;

import jakarta.persistence.criteria.JoinType;

import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;

public final class RetailSpecification {

    private RetailSpecification() {
    }

    public static Specification<Item> searchByName(final String name) {
        return (item, query, builder) -> builder.like(
                builder.lower(item.get("name")),
                "%" + name.toLowerCase(Locale.getDefault()) + "%");
    }

    public static Specification<Item> minSales(final Long number) {
        return (item, query, builder) -> builder.greaterThanOrEqualTo(
                item.get("sales_unit"),
                number);
    }

    public static Specification<Item> maxSales(final Long number) {
        return (item, query, builder) -> builder.lessThan(
                item.get("sales_unit"),
                number);
    }

    public static Specification<Item> stockInRangeAndStockType(
            final Long minStock, 
            final Long maxStock,
            final StockType stockType
    ) {
        return (root, query, builder) -> {
            query.distinct(true);
            root.join("stock", JoinType.INNER);

            if (minStock != null) {
                query.where(builder.greaterThanOrEqualTo(root.get("sales_unit"), minStock));
            }

            if (maxStock != null) {
                if (query.getRestriction() != null) {
                    query.where(
                            builder.and(query.getRestriction(), builder.lessThan(root.get("sales_unit"), maxStock)));
                } else {
                    query.where(builder.lessThan(root.get("sales_unit"), maxStock));
                }
            }

            if (stockType != null) {
                switch (stockType) {
                    case SMALL:
                        query.where(builder.isNotNull(root.get("stock").get("SMALL")));
                        break;
                    case MEDIUM:
                        query.where(builder.isNotNull(root.get("stock").get("MEDIUM")));
                        break;
                    case LARGE:
                        query.where(builder.isNotNull(root.get("stock").get("LARGE")));
                        break;
                    default:
                        break;
                }
            }

            return query.getRestriction();
        };
    }

}
