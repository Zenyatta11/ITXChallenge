package com.zenyatta.challenge.inditex.dto.filter;

import com.zenyatta.challenge.inditex.model.Item;
import com.zenyatta.challenge.inditex.model.Stock;
import com.zenyatta.challenge.inditex.model.StockType;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;

public final class RetailSpecification {

    public static Specification<Item> searchByName(final String name) {
        return (item, query, builder) -> builder.like(
                builder.lower(item.get("name")),
                "%" + name.toLowerCase(Locale.getDefault()) + "%");
    }

    public static Specification<Item> minSales(final Long number) {
        return (item, query, builder) -> builder.greaterThanOrEqualTo(
                item.get("salesUnit"),
                number);
    }

    public static Specification<Item> maxSales(final Long number) {
        return (item, query, builder) -> builder.lessThan(
                item.get("salesUnit"),
                number);
    }

    public static Specification<Item> stockInRangeAndStockType(
            final Long minStock,
            final Long maxStock,
            final StockType stockType
    ) {
            
        return (root, query, builder) -> {
            query.distinct(true);

            Join<Item, Stock> stockJoin = root.join("stock", JoinType.INNER);

            Expression<Long> sumAmount = builder.sumAsLong(stockJoin.get("amount"));

            if (minStock != null) {
                if (query.getGroupList().isEmpty()) {
                    query.groupBy(root.get("id"));
                }

                query.having(builder.greaterThanOrEqualTo(sumAmount, minStock));
            }

            if (maxStock != null) {
                if (query.getGroupList().isEmpty()) {
                    query.groupBy(root.get("id"));
                }

                query.having(builder.lessThan(sumAmount, maxStock));
            }

            if (stockType != null) {
                switch (stockType) {
                    case SMALL:
                        query.where(builder.isNotNull(stockJoin.get("SMALL")));
                        break;

                    case MEDIUM:
                        query.where(builder.isNotNull(stockJoin.get("MEDIUM")));
                        break;

                    case LARGE:
                        query.where(builder.isNotNull(stockJoin.get("LARGE")));
                        break;

                    default:
                        break;
                }
            }

            return query.getRestriction();
        };
    }

    private RetailSpecification() {
    }

}
