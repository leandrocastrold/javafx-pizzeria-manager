package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyData {

    public static List<PizzaFlavor> getFlavors() {
        List<PizzaFlavor> flavors = Arrays.asList(
                new PizzaFlavor("Muçarela", "Muçarela, com ovo, tomate e orégano", new BigDecimal("20.00")),
                new PizzaFlavor("Calabresa", "Calabresa, com queijo, tomate e cebola", new BigDecimal("25.00")),
                new PizzaFlavor("Portuguesa", "Portuguesa, com ovo, queijo, tomate e cebola", new BigDecimal("24.00")),
                new PizzaFlavor("Atum", "Atum, com queijo, tomate e cebola", new BigDecimal("23.00"))
        );
        return flavors;
    }

    public static List<PizzaBorder> getBorders() {
        List<PizzaBorder> pizzaBorders = new ArrayList<>();
        pizzaBorders.add(new PizzaBorder("Nenhuma", new BigDecimal(0)));
        pizzaBorders.add(new PizzaBorder("Cheddar", new BigDecimal("2.00")));
        pizzaBorders.add(new PizzaBorder("Catupiry", new BigDecimal("1.50")));
        pizzaBorders.add(new PizzaBorder("Requeijão", new BigDecimal("1.30")));
        return pizzaBorders;
    }

    public static List<PizzaAdditional> getAdditionals() {
        List<PizzaAdditional> additionals = Arrays.asList(
                new PizzaAdditional("Cheddar Extra", new BigDecimal("2.00")),
                new PizzaAdditional("Creme Cheese", new BigDecimal("1.50")),
                new PizzaAdditional("Muçarela Extra", new BigDecimal("1.80")),
                new PizzaAdditional("Parmesão Extra", new BigDecimal("3.00"))
        );

        return additionals;
    }

}
