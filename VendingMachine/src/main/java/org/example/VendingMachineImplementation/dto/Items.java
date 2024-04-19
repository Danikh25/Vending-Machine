package org.example.VendingMachineImplementation.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Items {
    private String name;
    private BigDecimal cost;
    private int inventory;

    public Items(String name, String cost, int inventory) {
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.inventory = inventory;
    }

    public Items(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return inventory == items.inventory && Objects.equals(name, items.name) && Objects.equals(cost, items.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, inventory);
    }

    @Override
    public String toString() {
        return "Items{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", inventory=" + inventory +
                '}';
    }
}
