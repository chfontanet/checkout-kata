package com.itv.kata.inventory.implementation;

import com.itv.kata.inventory.api.IAddItem;
import com.itv.kata.inventory.api.IName;
import com.itv.kata.item.Item;

public class AddItem implements IAddItem {

    private InventoryFactoryBuilder inventoryFactoryBuilder;
    private Item item;

    public AddItem(InventoryFactoryBuilder inventoryFactoryBuilder, Item item) {
        this.inventoryFactoryBuilder = inventoryFactoryBuilder;
        this.item = item;
    }

    @Override
    public Name addItem() {
        return inventoryFactoryBuilder.inventory();
    }

    @Override
    public StockKeepingUnits build() {
        return inventoryFactoryBuilder.build();
    }
}
