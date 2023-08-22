package com.company.gamestore.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Tshirt {

    private int id;

    private String size; // Let there be 3 sizes: small (S), medium (M), large (L)

    private String color; // Let there be 4 colors: Red, White, Black, Grey

    private String description;

    private BigDecimal price;

    private int quantity;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tshirt)) return false;
        Tshirt tshirt = (Tshirt) o;
        return getId() == tshirt.getId() && getQuantity() == tshirt.getQuantity() && Objects.equals(getSize(), tshirt.getSize()) && Objects.equals(getColor(), tshirt.getColor()) && Objects.equals(getDescription(), tshirt.getDescription()) && Objects.equals(getPrice(), tshirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "id=" + id +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
