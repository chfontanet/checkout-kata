### Description
Developed with `gradle`, and using only one external library, `Guava` from Google, to check valid Strings, compare objects and use ImmutableList in tests.
Using `Double` for prices, so you can use decimals.

Technical test created using builder pattern to initialize Stock Keeping Units

### Inicialization
- Initialization of Stock Keeping Units

```
final StockKeepingUnits sku = new InventoryFactoryBuilder().build().getInventory()
        .inventory()
            .name("A").price(50.0)
            .offer()
                .offerUnits(3).offerPrice(130.0)
        .addItem()
            .name("B").price(30.0)
            .offer()
                .offerUnits(2).offerPrice(45.0)
        .addItem()
            .name("C").price(20.0)
        .addItem()
            .name("D").price(15.0)
        .build();
```

### Testing
- Scanning items and checkout

```
final Double checkout = new Store(sku.getInventory().getItems())
        .scan("A").scan("A").scan("A")
        .scan("B").scan("B").scan("B")
        .scan("C")
        .scan("D")
        .checkout();
```
