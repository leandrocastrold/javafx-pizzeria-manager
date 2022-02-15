package models;

import enums.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

public class PizzaFactory {

    public static Pizza createPizza(PizzaFlavor flavor1, PizzaFlavor flavor2, List<PizzaAdditional> additionals, PizzaBorder border, BigDecimal price, PizzaSize size) {
        Pizza pizza = new Pizza();
        System.out.println("Sabor 1: " + flavor1 + " Sabor 2: " + flavor2 );
        if (flavor2 == null) {
            pizza = new Pizza(flavor1, border, price, size);
            System.out.println("Pizza Inteira - " + pizza);
        } else {
            System.out.println("Pizza Dividida - " + pizza);
            pizza = new Pizza(flavor1, flavor2, border, price, size);
        }

        for (PizzaAdditional adt: additionals) {
           Food temp = new PizzaAdditional(adt.getName(), pizza, adt.getPrice());
         //  pizza.setPrice(temp.cost());
           pizza.setDescription(temp.getDescription());
        }
        return pizza;
    }

}
