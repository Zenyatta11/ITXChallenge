package com.zenyatta.challenge.inditex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringExclude;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Entity
@Table(name = "stock_records", uniqueConstraints = @UniqueConstraint(columnNames = { "type", "stock_item_id" }))
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StockPK.class)
public class Stock {
    @Id
    @JsonIgnore
    @ToStringExclude
    @Column(name = "stock_item_id", nullable = false)
    private Long stockItemId;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private StockType stockType;

    @Column(name = "amount", nullable = false)
    private Long amount;

}
