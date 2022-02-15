package models;

import enums.PizzaSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.border.Border;
import java.math.BigDecimal;

@Entity
public class Pizza implements Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private PizzaFlavor flavor1;
    private PizzaFlavor flavor2;
    private PizzaBorder border;
    private BigDecimal price;
    private PizzaSize size;

    public Pizza() {
    }

    //Pizza inteira
    public Pizza(PizzaFlavor flavor1, PizzaBorder border, BigDecimal price, PizzaSize size) {
        this.size = size;
        this.name = "Pizza " + flavor1.getName() + " " + size.description;
        this.flavor1 = flavor1;
        this.border = border;
        this.price = price;
        this.description = "Pizza " + size.description + " de " + flavor1.getDescription() + ". BORDA = " + border.getName();
    }
    //Pizza Dividida


    public Pizza(PizzaFlavor flavor1, PizzaFlavor flavor2, PizzaBorder border, BigDecimal price, PizzaSize size) {
        this.size = size;
        this.name = "Pizza "+ size.description + " " + flavor1.getName() + " / " + flavor2.getName();
        this.description = "Pizza " + size.description + " dividida: Metade 1: " + flavor1.getDescription() + "\nMetade 2: " + flavor2.getDescription() + ". \nBORDA = " + border.getName();
        this.flavor1 = flavor1;
        this.flavor2 = flavor2;
        this.border = border;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PizzaBorder getBorder() {
        return border;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBorder(PizzaBorder border) {
        this.border = border;
    }

    public PizzaFlavor getFlavor1() {
        return flavor1;
    }

    public void setFlavor1(PizzaFlavor flavor1) {
        this.flavor1 = flavor1;
    }

    public PizzaFlavor getFlavor2() {
        return flavor2;
    }

    public void setFlavor2(PizzaFlavor flavor2) {
        this.flavor2 = flavor2;
    }

    public BigDecimal getPrice() {

        return price.add(border.getPrice()).multiply(size.sizeMultiplier);
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", flavor1=" + flavor1 +
                ", flavor2=" + flavor2 +
                ", border=" + border +
                ", price=" + price +
                '}';
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal cost() {
        return price;
    }
}
