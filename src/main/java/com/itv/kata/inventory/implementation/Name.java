package com.itv.kata.inventory.implementation;

import com.google.common.base.Strings;
import com.itv.kata.inventory.api.IName;
import com.itv.kata.item.Item;

public class Name implements IName {

    private InventoryFactoryBuilder inventoryFactoryBuilder;
    private Item item;

    public Name(InventoryFactoryBuilder inventoryFactoryBuilder, Item item) {
        this.inventoryFactoryBuilder = inventoryFactoryBuilder;
        this.item = item;
    }

    @Override
    public UnitPrice name(String name) {
        if (Strings.isNullOrEmpty(name)) throw new IllegalArgumentException("Invalid name for item [" + name + "]");
        if (inventoryFactoryBuilder.getInventory().existsProduct(name))
            throw new IllegalArgumentException("Name '" + name + "' already exists");

        item.setName(name);
        return new UnitPrice(inventoryFactoryBuilder, item);
    }
}
