package models;

import java.math.BigDecimal;

public class PizzaAdditional implements Food{
    private String name;
    private Food pizza;
    private BigDecimal price;

    public PizzaAdditional() {
    }

    public PizzaAdditional(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public PizzaAdditional(String name, Food pizza, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.pizza = pizza;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Food getPizza() {
        return pizza;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPizza(Food pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + "\nEXTRA = " + this.name;
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(this.pizza.cost().doubleValue() + price.doubleValue());
    }

    @Override
    public String toString() {
        return "PizzaAdditional{" +
                "name='" + name + '\'' +
                ", pizza=" + pizza +
                ", price=" + price +
                '}';
    }
}
