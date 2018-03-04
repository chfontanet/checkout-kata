package com.itv.kata.inventory.implementation;

public class StockKeepingUnits {

    private Inventory inventory;

    public StockKeepingUnits(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
