package controllers;

import application.Main;
import enums.PizzaItemStatus;
import enums.PizzaOrderStatus;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import models.PizzaItem;
import models.PizzaOrder;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KitchenController implements Initializable {
    private List<PizzaOrder> pizzaOrders;

    @FXML
    private VBox pizzaOrdersVbox;
    @FXML
    private VBox pizzaOrderitemsVbox;
    @FXML
    private VBox orderHistoricVbox;
    @FXML
    private Button btConfirm;
    @FXML
    private Button btCancel;

    private PizzaOrder currentOrder;

    private Boolean defineBooleanFromPizzaItemStatus(PizzaItemStatus status) {
        Boolean currentStatus = false;
        switch (status) {
            case WAITING:
                currentStatus = false;
                break;
            case READY:
                currentStatus = true;
                break;
        }
        return currentStatus;
    }

    private PizzaItemStatus getItemStatusFromBoolean(Boolean condition) {
        return !condition? PizzaItemStatus.WAITING : PizzaItemStatus.READY;
    }

    private void onCheckboxChangeValue(CheckBox checkBox, PizzaItem item) {
        Boolean result = checkBox.selectedProperty().getValue();
        checkBox.selectedProperty().setValue(result);
        item.setStatus(getItemStatusFromBoolean(result));
        System.out.println(item + ": " + item.getStatus());
        setControlButtonsCondition();
    }

    public void sendOrdersToHistoricIfAllItemsAreReady() {
        clearOrderHistoric();
        for (PizzaOrder order : pizzaOrders) {

            addOrderToHistoricIfItIsReady(order);
            fillPizzaOrderVboxWithOrdersButtons();
            clearPizzaOrderItemsVbox();
            selectNextOrderIfItExists();
            setControlButtonsCondition();

        }
    }

    public void onCancelOrderButtonAction() {
        cancelCurrentOrder();
        selectNextOrderIfItExists();
        clearPizzaOrderItemsVbox();
        fillPizzaOrderVboxWithOrdersButtons();
        fillPizzaItemsCheckboxes(currentOrder);
        setControlButtonsCondition();
    }

    public void cancelCurrentOrder() {
        if (currentOrder.getStatus() == PizzaOrderStatus.WAITING) {
            currentOrder.setStatus(PizzaOrderStatus.CANCELED);
        }
    }

    private void selectNextOrderIfItExists() {
        currentOrder = null;
        for (PizzaOrder order : pizzaOrders) {
            if (order.getStatus() == PizzaOrderStatus.WAITING) {
                currentOrder = order;
                break;
            }
        }
    }

    private void clearOrderHistoric() {
        orderHistoricVbox.getChildren().clear();
    }

    private void clearPizzaOrderItemsVbox() {
        pizzaOrderitemsVbox.getChildren().clear();
    }

    private void addOrderToHistoricIfItIsReady(PizzaOrder order) {
        order.changeStatusFromItemsCondition();
        if (order.getStatus() == PizzaOrderStatus.READY) {
            Button bt = createButtonFromAGivenPizzaOrder(order);
            orderHistoricVbox.getChildren().add(bt);
        }
    }


    private Button createButtonFromAGivenPizzaOrder(PizzaOrder order) {

        Button bt = new Button(order.getId() + " - " + order.getClient());
        bt.onActionProperty().setValue(p -> fillPizzaItemsCheckboxes(order));
        bt.setPrefWidth(Double.MAX_VALUE);
        bt.setTextAlignment(TextAlignment.LEFT);
        return bt;
    }

    private void fillPizzaItemsCheckboxes(PizzaOrder order) {
        if (order != null) {
            pizzaOrderitemsVbox.getChildren().clear();
            currentOrder = order;
            for (PizzaItem item : currentOrder.getItems()) {
                CheckBox checkBox = new CheckBox(item.toString());
                checkBox.selectedProperty().setValue(defineBooleanFromPizzaItemStatus(item.getStatus()));
                checkBox.onActionProperty().setValue(action -> onCheckboxChangeValue(checkBox, item));
                pizzaOrderitemsVbox.getChildren().add(checkBox);
                checkBox.setDisable(order.getStatus() != PizzaOrderStatus.WAITING);
            }
            setControlButtonsCondition();
        }
    }

    private void fillPizzaOrderVboxWithOrdersButtons() {
        pizzaOrdersVbox.getChildren().clear();
        for (PizzaOrder order : pizzaOrders) {
            if (order.getStatus() == PizzaOrderStatus.WAITING) {
                Button bt = createButtonFromAGivenPizzaOrder(order);
                pizzaOrdersVbox.getChildren().add(bt);
            }
        }
    }

    private void setControlButtonsCondition() {
        btCancel.setDisable(true);
        btConfirm.setDisable(true);
        if (currentOrder != null && currentOrder.getStatus() == PizzaOrderStatus.WAITING) {
            currentOrder.changeStatusFromItemsCondition();
            btCancel.setDisable(false);
            boolean isEnable = currentOrder.getStatus() == PizzaOrderStatus.READY;
            btConfirm.setDisable(!isEnable);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizzaOrders = Main.getPizzaOrders();
        sendOrdersToHistoricIfAllItemsAreReady();
        fillPizzaOrderVboxWithOrdersButtons();
        System.out.println(pizzaOrders);
    }
}
