package com.zenyatta.challenge.inditex.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPK implements Serializable {

    private Long stockItemId;
    private StockType stockType;

}