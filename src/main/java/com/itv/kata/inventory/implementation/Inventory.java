package com.itv.kata.inventory.implementation;

import com.itv.kata.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private InventoryFactoryBuilder inventoryFactoryBuilder;
    private List<Item> items;

    public Inventory(InventoryFactoryBuilder inventoryFactoryBuilder) {
        this.inventoryFactoryBuilder = inventoryFactoryBuilder;
        this.items = new ArrayList<>();
    }

    public Name inventory() {
        final Item item = new Item();
        this.items.add(item);
        return new Name(inventoryFactoryBuilder, item);
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean existsProduct(String name) {
        return items.stream().anyMatch(item -> name.equals(item.getName()));
    }
}
