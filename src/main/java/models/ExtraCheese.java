package models;

import java.math.BigDecimal;

public class ExtraCheese implements Food{

    Food pizza;
    private BigDecimal price;
    public ExtraCheese(Food pizza, BigDecimal price){
        this.pizza = pizza;
        this.price = price;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + ", Queijo Extra";
    }

    @Override
    public BigDecimal cost() {
        return new BigDecimal(this.pizza.cost().doubleValue() + this.price.doubleValue());
    }
}
