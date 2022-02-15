package models;

import java.math.BigDecimal;

public class PizzaFlavor {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;

    public PizzaFlavor() {
    }

    public PizzaFlavor(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PizzaFlavor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
