package com.itv.kata.inventory.implementation;

import com.itv.kata.inventory.api.IOfferPrice;
import com.itv.kata.item.Item;

public class OfferPrice implements IOfferPrice {

    private InventoryFactoryBuilder inventoryFactoryBuilder;
    private Item item;

    public OfferPrice(InventoryFactoryBuilder inventoryFactoryBuilder, Item item) {
        this.inventoryFactoryBuilder = inventoryFactoryBuilder;
        this.item = item;
    }

    @Override
    public AddItem offerPrice(Double price) {
        if ((price == null) || (price <= 0.0))
            throw new IllegalArgumentException("Invalid price for item [" + price + "]");
        this.item.setSpecialPrice(price);

        return new AddItem(inventoryFactoryBuilder, item);
    }
}
