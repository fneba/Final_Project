package com.company.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console {

    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "You must supply a string for model.")
    private String model;

    @NotEmpty(message = "You must supply a string for manufacturer.")
    private String manufacturer;
    private String memory_amount;
    private String processor;
    @NotEmpty(message = "You must supply a string for price.")
    private int price;

    @NotEmpty(message = "You must supply a value for quantity.")
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return id == console.id && price == console.price && quantity == console.quantity && Objects.equals(model, console.model) && Objects.equals(manufacturer, console.manufacturer) && Objects.equals(memory_amount, console.memory_amount) && Objects.equals(processor, console.processor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, manufacturer, memory_amount, processor, price, quantity);
    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memory_amount='" + memory_amount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
