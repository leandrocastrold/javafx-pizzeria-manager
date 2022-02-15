package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.PizzaOrder;
import org.hibernate.criterion.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static Scene mainScene;
    private static List<PizzaOrder> pizzaOrders;

    public static void main(String[] args) {
        pizzaOrders = new ArrayList<>();
        launch(args);
    }

    public static void addPizzaOrder(PizzaOrder order) {
        pizzaOrders.add(order);
    }

    public static List<PizzaOrder> getPizzaOrders(){
        return pizzaOrders;
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
            Parent parent = loader.load();
            mainScene = new Scene(parent);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Pizzaria");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


