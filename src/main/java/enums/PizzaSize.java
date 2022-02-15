package enums;

import java.math.BigDecimal;

public enum PizzaSize {
    LITTLE(new BigDecimal(0.75), "pequena"), AVERAGE(new BigDecimal(0.85), "média"), BIG(new BigDecimal(1.0), "grande");

    public final BigDecimal sizeMultiplier;
    public final String description;

    PizzaSize(BigDecimal value, String description) {
    this.sizeMultiplier = value;
    this.description = description;
    }
}
