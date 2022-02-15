package models;

import java.math.BigDecimal;

public class PizzaBorder {

    private Integer id;
    private String name;
    private BigDecimal price;

    public PizzaBorder() {
    }

    public PizzaBorder(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
