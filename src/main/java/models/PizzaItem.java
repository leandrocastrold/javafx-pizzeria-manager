package models;

import enums.PizzaItemStatus;

import java.math.BigDecimal;

public class PizzaItem {

    PizzaItemStatus status;
    private Integer id;
    private Integer pizzaQuantity;
    private Food pizzaType;
    private BigDecimal subtotal;

    public PizzaItem() {
        this.status = PizzaItemStatus.WAITING;
    }

    public PizzaItem(Integer pizzaQuantity, Food pizzaType) {
        this.pizzaQuantity = pizzaQuantity;
        this.pizzaType = pizzaType;
        this.status = PizzaItemStatus.WAITING;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getSubtotal() {
        subtotal = new BigDecimal(0);
        subtotal = pizzaType.cost().multiply(new BigDecimal(pizzaQuantity));
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public PizzaItemStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaItemStatus status) {
        this.status = status;
    }

    public Integer getPizzaQuantity() {
        return pizzaQuantity;
    }

    public void setPizzaQuantity(Integer pizzaQuantity) {
        this.pizzaQuantity = pizzaQuantity;
    }

    public Food getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(Pizza pizzaType) {
        this.pizzaType = pizzaType;
    }

//    2x - Pizza de Calabreza - Cheddar, Creme Cheese, Provolone Extra
//    R$: 20,00;
    @Override
    public String toString() {
        return pizzaQuantity+"x - " + pizzaType.getDescription() + "\n R$: " + String.format("%.2f", getSubtotal());
    }
}
