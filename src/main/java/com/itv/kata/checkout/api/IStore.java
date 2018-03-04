package com.itv.kata.checkout.api;

import com.itv.kata.checkout.implementation.Store;
import com.itv.kata.item.Item;

import java.util.List;

public interface IStore {
    Store scan(String name);
    Double checkout();
}
