package com.itv.kata.item;

import com.google.common.base.Objects;

public class Item {

    private String name;
    private Double unitPrice;
    private Integer specialUnits;
    private Double specialPrice;

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Item setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Integer getSpecialUnits() {
        return specialUnits;
    }

    public Item setSpecialUnits(Integer specialUnits) {
        this.specialUnits = specialUnits;
        return this;
    }

    public Double getSpecialPrice() {
        return specialPrice;
    }

    public Item setSpecialPrice(Double specialPrice) {
        this.specialPrice = specialPrice;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equal(name, item.name) &&
                Objects.equal(unitPrice, item.unitPrice) &&
                Objects.equal(specialUnits, item.specialUnits) &&
                Objects.equal(specialPrice, item.specialPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, unitPrice, specialUnits, specialPrice);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", specialUnits=" + specialUnits +
                ", specialPrice=" + specialPrice +
                '}';
    }
}
