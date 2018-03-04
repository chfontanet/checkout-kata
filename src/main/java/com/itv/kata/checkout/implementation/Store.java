package com.itv.kata.checkout.implementation;

import com.google.common.base.Strings;
import com.itv.kata.checkout.api.IStore;
import com.itv.kata.item.Item;
import com.itv.kata.item.ShoppingList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Store implements IStore {
    private Map<String, ShoppingList> shoppingListMap = new HashMap<>();
    private Map<String, Item> items;

    public Store(List<Item> items) {
        this.items = items.stream().collect(Collectors.toMap(Item::getName, it -> it));
    }


    @Override
    public Store scan(String name) {
        if (Strings.isNullOrEmpty(name)) throw new IllegalArgumentException("Invalid name for item [" + name + "]");
        if (!items.containsKey(name)) throw new IllegalArgumentException("Item '" + name + "' is not in stock");

        // Existing item
        if (shoppingListMap.containsKey(name)) {
            shoppingListMap.get(name).addUnit();
        }
        // New item
        else {
            final ShoppingList item = new ShoppingList().setName(name).addUnit();
            shoppingListMap.put(name, item);
        }
        return this;
    }

    @Override
    public Double checkout() {
        if ((items == null) || (items.isEmpty())) throw new IllegalArgumentException("Stock Keeping Units is empty!");
        if ((shoppingListMap == null) || (shoppingListMap.isEmpty())) return 0.0;

        return shoppingListMap.values().stream().mapToDouble(this::sumGoods).sum();
    }

    private double sumGoods(ShoppingList shoppingList) {
        final String name = shoppingList.getName();
        final int units = shoppingList.getUnits();

        final Item item = items.get(name);
        // Unit price
        if (units == 1) return item.getUnitPrice(); // Only one good (normal price)

        if (units < item.getSpecialUnits()) return units * item.getUnitPrice(); // More than one good but not enough to special price

        //Special price
        if (units == item.getSpecialUnits()) return item.getSpecialPrice(); // Exact units for one special price

        int unitsInOffer = units / item.getSpecialUnits(); // One or more unit packs in special price
        int unitsInNormalPrice = units % item.getSpecialUnits(); // Remaining items in normal price

        return (unitsInOffer * item.getSpecialPrice()) + (unitsInNormalPrice * item.getUnitPrice());
    }
}
