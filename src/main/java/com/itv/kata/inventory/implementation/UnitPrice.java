package com.itv.kata.inventory.implementation;

import com.itv.kata.inventory.api.IUnitPrice;
import com.itv.kata.item.Item;

public class UnitPrice implements IUnitPrice {

    private InventoryFactoryBuilder inventoryFactoryBuilder;
    private Item item;

    public UnitPrice(InventoryFactoryBuilder inventoryFactoryBuilder, Item item) {
        this.inventoryFactoryBuilder = inventoryFactoryBuilder;
        this.item = item;
    }

    @Override
    public UnitPrice price(Double price) {
        if ((price == null) || (price <= 0.0))
            throw new IllegalArgumentException("Invalid price for item [" + price + "]");
        this.item.setUnitPrice(price);
        return this;
    }

    @Override
    public OfferUnits offer() {
        return new OfferUnits(inventoryFactoryBuilder, item);
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
