package com.zenyatta.challenge.inditex.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@Entity
@Table(name = "stock_records", uniqueConstraints = @UniqueConstraint(columnNames = "item_id"))
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @Column(name = "item_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "small", nullable = false)
    private Long small;

    @Column(name = "medium")
    private Long medium;

    @Column(name = "large")
    private Long large;
}
