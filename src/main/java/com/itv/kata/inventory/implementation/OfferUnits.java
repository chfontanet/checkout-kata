package com.itv.kata.inventory.implementation;

import com.itv.kata.inventory.api.IOfferUnits;
import com.itv.kata.item.Item;

public class OfferUnits implements IOfferUnits {

    private InventoryFactoryBuilder inventoryFactoryBuilder;
    private Item item;

    public OfferUnits(InventoryFactoryBuilder inventoryFactoryBuilder, Item item) {
        this.inventoryFactoryBuilder = inventoryFactoryBuilder;
        this.item = item;
    }

    @Override
    public OfferPrice offerUnits(Integer units) {
        if ((units == null) || (units <= 0))
            throw new IllegalArgumentException("Invalid number of units [" + units + "]");
        item.setSpecialUnits(units);

        return new OfferPrice(inventoryFactoryBuilder, item);
    }
}
