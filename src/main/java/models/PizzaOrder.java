package models;

import enums.PizzaItemStatus;
import enums.PizzaOrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PizzaOrder {

    private PizzaOrderStatus status;
    private Integer id;
    private String client;
    private List<PizzaItem> items;
    private BigDecimal total;

    public PizzaOrder() {
        items = new ArrayList<>();
        total = new BigDecimal("0");
        this.status = PizzaOrderStatus.WAITING;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public PizzaOrderStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaOrderStatus status) {
        this.status = status;
    }

    public List<PizzaItem> getItems() {
        return items;
    }

    public void setItems(List<PizzaItem> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        total = new BigDecimal(0);
        for (PizzaItem item : items) {
            total = total.add(item.getSubtotal());
            System.out.println("Subtotal ITEM = " + total);
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void changeStatusFromItemsCondition(){
        boolean result = true;
        for (PizzaItem item : items) {
            if (item.status == PizzaItemStatus.WAITING) {
                result = false;
            }
        }
        if (result) {
            status = PizzaOrderStatus.READY;
        } else {
            status = PizzaOrderStatus.WAITING;
        }
    }

    @Override
    public String toString() {
        return "models.PizzaOrder{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", items=" + items.toString() +
                ", subTotal=" + getTotal() +
                '}';
    }
}
