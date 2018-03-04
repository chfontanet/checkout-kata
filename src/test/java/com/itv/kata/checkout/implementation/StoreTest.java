package com.itv.kata.checkout.implementation;

import com.google.common.collect.ImmutableList;
import com.itv.kata.checkout.api.IStore;
import com.itv.kata.inventory.implementation.InventoryFactoryBuilder;
import com.itv.kata.inventory.implementation.StockKeepingUnits;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StoreTest {
    private Store store;
    private StockKeepingUnits sku;

    @Before
    public void setUp() {
        sku = new InventoryFactoryBuilder().build().getInventory()
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
        store = new Store(sku.getInventory().getItems());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptySTU() {
        Store storeTest = new Store(ImmutableList.of());
        storeTest.scan("B").checkout();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidItem() {
        store.scan("NO");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyItem() {
        store.scan("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullItem() {
        store.scan(null);
    }

    @Test
    public void testEmptyCheckout() {
        final Double checkout = store.checkout();
        assertEquals(checkout, Double.valueOf(0.0));
    }

    @Test
    public void testSimpleCheckout() {
        final Double checkout = store.scan("A").checkout();
        assertEquals(checkout, Double.valueOf(50.0));
    }

    @Test
    public void testExampleExercise() {
        final Double checkout = store.scan("B").scan("A").scan("B").checkout();
        assertEquals(checkout, Double.valueOf(95.0));
    }

    @Test
    public void testBuyOnlyOneOffer() {
        final Double checkout = store.scan("B").scan("B")
                .checkout();
        assertEquals(checkout, Double.valueOf(45.0));
    }

    @Test
    public void testBuyTwoTimesOffer() {
        final Double checkout = store.scan("B").scan("B").scan("B").scan("B")
                .checkout();
        assertEquals(checkout, Double.valueOf(90.0));
    }

    @Test
    public void testBuyTwoTimesOfferRemainingNormalPrice() {
        final Double checkout = store.scan("B").scan("B").scan("B").scan("B").scan("B")
                .checkout();
        assertEquals(checkout, Double.valueOf(120.0));
    }

    @Test
    public void testBuyAllWithOffers() {
        final Double checkout = store.scan("A").scan("A").scan("A")
                .scan("B").scan("B")
                .scan("C")
                .scan("D")
                .checkout();
        assertEquals(checkout, Double.valueOf(210.0));
    }

    @Test
    public void testBuyAllWithoutOffers() {
        final Double checkout = store.scan("A").scan("A")
                .scan("B")
                .scan("C")
                .scan("D")
                .checkout();
        assertEquals(checkout, Double.valueOf(165.0));
    }

    @Test
    public void testBuyAllWithOffersAndOneInNormalPrice() {
        final Double checkout = store.scan("A").scan("A").scan("A")
                .scan("B").scan("B").scan("B")
                .scan("C")
                .scan("D")
                .checkout();
        assertEquals(checkout, Double.valueOf(240.0));
    }
}