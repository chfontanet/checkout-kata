package com.itv.kata.inventory.implementation;

import com.itv.kata.item.Item;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class InventoryFactoryBuilderTest {

    private StockKeepingUnits stockKeepingUnits;

    @Test
    public void testInitSTU() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        assertNotNull(stockKeepingUnits);
    }

    @Test
    public void testInventoryNotInitialized() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Inventory inventory = stockKeepingUnits.getInventory();
        assertNotNull(inventory.getItems());
        assertTrue(inventory.getItems().isEmpty());
    }

    @Test
    public void testInventoryInitialized() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Inventory inventory = stockKeepingUnits.getInventory();
        Name name = inventory.inventory();
        assertFalse(inventory.getItems().isEmpty());
        assertNotNull(name);
    }

    @Test
    public void testSetName() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Name name = stockKeepingUnits.getInventory().inventory();
        UnitPrice unitPrice = name.name("A");
        assertNotNull(unitPrice);
        assertTrue(stockKeepingUnits.getInventory().getItems().stream().allMatch(it -> it.getName().equals("A")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyName() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Name name = stockKeepingUnits.getInventory().inventory();
        name.name("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullName() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Name name = stockKeepingUnits.getInventory().inventory();
        name.name(null);
    }

    @Test
    public void testSetPrice() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Name name = stockKeepingUnits.getInventory().inventory();
        UnitPrice unitPrice = name.name("A").price(50.0);
        assertNotNull(unitPrice);
        assertTrue(stockKeepingUnits.getInventory().getItems().stream().allMatch(it -> it.getUnitPrice().equals(50.0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidPrice() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        stockKeepingUnits.getInventory().inventory().name("A").price(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNegativePrice() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        stockKeepingUnits.getInventory().inventory().name("A").price(-10.0);
    }

    @Test
    public void testOffer() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        OfferUnits offerUnits = stockKeepingUnits.getInventory().inventory().name("A").price(50.0).offer();
        assertNotNull(offerUnits);
    }

    @Test
    public void testAddOfferUnits() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        OfferPrice offerPrice = stockKeepingUnits.getInventory().inventory().name("A").price(50.0).offer().offerUnits(3);
        assertNotNull(offerPrice);
        final Optional<Item> item = stockKeepingUnits.getInventory().getItems().stream().findFirst();
        assertTrue(item.get().getSpecialUnits().equals(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOfferInvalidUnits() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        stockKeepingUnits.getInventory().inventory().name("A").price(50.0).offer().offerUnits(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOfferNegativeUnits() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        stockKeepingUnits.getInventory().inventory().name("A").price(50.0).offer().offerUnits(-3);
    }

    @Test
    public void testAddOfferPrice() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        AddItem addItem = stockKeepingUnits.getInventory().inventory()
                .name("A").price(50.0).offer().offerUnits(3).offerPrice(130.0);
        assertNotNull(addItem);
        final Optional<Item> item = stockKeepingUnits.getInventory().getItems().stream().findFirst();
        assertTrue(item.get().getSpecialPrice().equals(130.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOfferInvalidPrice() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        stockKeepingUnits.getInventory().inventory()
                .name("A").price(50.0).offer().offerUnits(3).offerPrice(0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddOfferNegativePrice() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        stockKeepingUnits.getInventory().inventory()
                .name("A").price(50.0).offer().offerUnits(3).offerPrice(-130.0);
    }

    @Test
    public void testSetSecondName() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Name name = stockKeepingUnits.getInventory().inventory();
        UnitPrice unitPrice = name.name("A").price(50.0).addItem().name("B");
        assertNotNull(unitPrice);
        assertTrue(stockKeepingUnits.getInventory().getItems().stream()
                .allMatch(it -> (it.getName().equals("A")) || it.getName().equals("B")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetExistingName() {
        stockKeepingUnits = new InventoryFactoryBuilder().build();
        Name name = stockKeepingUnits.getInventory().inventory();
        name.name("A").price(50.0).addItem().name("A");

    }

    @Test
    public void testCreateExampleExercise() {
        final Inventory inventory = new InventoryFactoryBuilder().build().getInventory();
        final StockKeepingUnits sku = inventory
                .inventory()
                    .name("A")
                    .price(50.0)
                    .offer()
                        .offerUnits(3)
                        .offerPrice(130.0)
                .addItem()
                    .name("B")
                    .price(30.0)
                    .offer()
                        .offerUnits(2)
                        .offerPrice(45.0)
                .addItem()
                    .name("C")
                    .price(20.0)
                .addItem()
                    .name("D")
                    .price(15.0)
                .build();

        final List<Item> items = sku.getInventory().getItems();

        final List<Item> itemsExample = getResponseExampleExercise();
        assertEquals(items.size(), itemsExample.size());
        assertEquals(items, itemsExample);
    }

    private List<Item> getResponseExampleExercise() {

        List<Item> items = new ArrayList<>();
        items.add(new Item().setName("A").setUnitPrice(50.0).setSpecialUnits(3).setSpecialPrice(130.0));
        items.add(new Item().setName("B").setUnitPrice(30.0).setSpecialUnits(2).setSpecialPrice(45.0));
        items.add(new Item().setName("C").setUnitPrice(20.0));
        items.add(new Item().setName("D").setUnitPrice(15.0));

        return items;
    }
}