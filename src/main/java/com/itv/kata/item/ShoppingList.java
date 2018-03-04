package com.itv.kata.item;

import com.google.common.base.Objects;

public class ShoppingList {
    private String name;
    private Integer units = 0;

    public String getName() {
        return name;
    }

    public ShoppingList setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getUnits() {
        return units;
    }

    public ShoppingList setUnits(Integer units) {
        this.units = units;
        return this;
    }

    public ShoppingList addUnit() {
        this.units++;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(units, that.units);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, units);
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }
}
