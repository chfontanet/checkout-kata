package com.itv.kata.inventory.api;

import com.itv.kata.inventory.implementation.StockKeepingUnits;

public interface IAddItem {

    IName addItem();
    StockKeepingUnits build();
}
