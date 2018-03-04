package com.itv.kata.inventory.implementation;

public class InventoryFactoryBuilder {

    private Inventory inventory;

    public InventoryFactoryBuilder() {
        this.inventory = new Inventory(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Name inventory() {
        return this.inventory.inventory();
    }

    public StockKeepingUnits build() {
        return new StockKeepingUnits(inventory);
    }
}
