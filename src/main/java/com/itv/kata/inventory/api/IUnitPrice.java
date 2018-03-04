package com.itv.kata.inventory.api;

public interface IUnitPrice extends IAddItem {

    IUnitPrice price(Double price);
    IOfferUnits offer();
}
