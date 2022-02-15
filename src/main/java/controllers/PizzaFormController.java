package controllers;

import application.Main;
import enums.PizzaItemStatus;
import enums.PizzaOrderStatus;
import enums.PizzaSize;
import gui.util.Alerts;
import gui.util.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import models.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;


public class PizzaFormController implements Initializable {

    @FXML
    private TextField tfClient;
    @FXML
    private TextField tfPizzaQty;
    @FXML
    private RadioButton rbSizeBig;
    @FXML
    private RadioButton rbSizeMedium;
    @FXML
    private RadioButton rbSizeLitte;
    @FXML
    private ToggleGroup rbSizeGroup;
    @FXML
    private RadioButton rbFull;
    @FXML
    private RadioButton rbDivided;
    @FXML
    private ToggleGroup rbGroup;
    @FXML
    private ComboBox<PizzaBorder> cbPizzaBorder;
    @FXML
    private ComboBox<PizzaFlavor> cbFlavor1;
    @FXML
    private ComboBox<PizzaFlavor> cbFlavor2;
    @FXML
    private Label lbFlavor2;
    @FXML
    private ComboBox<PizzaAdditional> cbAdditional;
    @FXML
    private Button btAdditional;
    @FXML
    private TextArea tfAdditional;
    @FXML
    private Label lbItemSubtotal;
    @FXML
    Label lbPizzaPrice;
    @FXML
    private TextArea taHistoric;
    @FXML
    private Label lbOrderTotal;
    @FXML
    private Button btAddItem;
    @FXML
    private Button btCloseOrder;

    private List<PizzaAdditional> additionalList;


    List<PizzaFlavor> flavors = new ArrayList<>();
    List<BigDecimal> additionalPrices = new ArrayList<>();
    private PizzaOrder pizzaOrder;
    private PizzaItem pizzaItem;
    private Pizza tempPizza;
    private Pizza finalPizza;


    @FXML
    public void checkPizzaType() {
        RadioButton radio = (RadioButton) rbGroup.getSelectedToggle();
        if (radio.getText().equals(rbFull.getText())) {
            System.out.println("Pizza INTEIRA selecionada");
            tempPizza.setFlavor2(null);
            lbFlavor2.setDisable(true);
            cbFlavor2.setDisable(true);
            calculatePizzaItemPrice();
        } else {
            System.out.println("Pizza DIVIDIDA selecionada");
            lbFlavor2.setDisable(false);
            cbFlavor2.setDisable(false);
            calculatePizzaItemPrice();
        }
    }


    @FXML
    public void calculatePizzaPrice() {
        Double currentPrice = 0.00;
        if (tempPizza.getFlavor1() != null) {
            if (tempPizza.getFlavor2() != null) {
                currentPrice += tempPizza.getFlavor1().getPrice().doubleValue() / 2;
                currentPrice += tempPizza.getFlavor2().getPrice().doubleValue() / 2;

            } else {
                currentPrice += tempPizza.getFlavor1().getPrice().doubleValue();
            }

            //Adiciona o pre�o dos adicionais escolhidos a pizza
            currentPrice = applyAdditionalsPrice(currentPrice);
            //  currentPrice = setPizzaPriceBySize(currentPrice);
            tempPizza.setPrice(new BigDecimal(currentPrice));

        } else {
            System.out.println("Preço da Pizza está nulo");
        }
    }

    @FXML
    public void onPizzaSizeRadioButtonAction() {
        tempPizza.setSize(radioPizzaSizeChange());
        calculatePizzaPrice();
        calculatePizzaItemPrice();
    }

    public PizzaSize radioPizzaSizeChange() {
        RadioButton rbSelected = (RadioButton) rbSizeGroup.getSelectedToggle();
        PizzaSize size;
        if (rbSelected.getText().equals(rbSizeLitte.getText())) {
            size = PizzaSize.LITTLE;
        } else if (rbSelected.getText().equals(rbSizeMedium.getText())) {
            size = PizzaSize.AVERAGE;
        } else {
            size = PizzaSize.BIG;
        }
        return size;
    }

    private Double applyAdditionalsPrice(Double pizzaPrice) {
        for (BigDecimal b : additionalPrices) {
            pizzaPrice += b.doubleValue();
        }
        return pizzaPrice;
    }

    @FXML
    public void calculatePizzaItemPrice() {
        if (isFormValid()) {
            lbItemSubtotal.setText("R$ " + String.format("%.2f", tempPizza.getPrice().multiply(new BigDecimal(tfPizzaQty.getText()))));
        }
        disableOrderButtons();
    }


    @FXML
    public void fillAdditionalList() {

        List<PizzaAdditional> additionals = DummyData.getAdditionals();
        ObservableList<PizzaAdditional> obsAdditional = FXCollections.observableList(additionals);
        cbAdditional.setItems(obsAdditional);

        Callback<ListView<PizzaAdditional>, ListCell<PizzaAdditional>> factory = lv -> new ListCell<PizzaAdditional>() {
            @Override
            protected void updateItem(PizzaAdditional item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Selecione um sabor..." : item.getName());
            }
        };
        cbAdditional.setCellFactory(factory);
        cbAdditional.setButtonCell(factory.call(null));
    }

    public void fillBordersList() {
        List<PizzaBorder> borders = DummyData.getBorders();
        ObservableList<PizzaBorder> obsBordersList = FXCollections.observableList(borders);
        cbPizzaBorder.setItems(obsBordersList);
        Callback<ListView<PizzaBorder>, ListCell<PizzaBorder>> factory = lv -> new ListCell<PizzaBorder>() {
            @Override
            protected void updateItem(PizzaBorder item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Selecione uma Borda..." : item.getName());
            }
        };
        cbPizzaBorder.setCellFactory(factory);
        cbPizzaBorder.setButtonCell(factory.call(null));
    }

    @FXML
    public void fillFlavorsList() {

        flavors = DummyData.getFlavors();
        ObservableList<PizzaFlavor> obs = FXCollections.observableList(flavors);
        cbFlavor1.setItems(obs);
        cbFlavor2.setItems(obs);

        Callback<ListView<PizzaFlavor>, ListCell<PizzaFlavor>> factory = lv -> new ListCell<PizzaFlavor>() {
            @Override
            protected void updateItem(PizzaFlavor item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "Selecione um sabor..." : item.getName());
            }
        };
        cbFlavor1.setCellFactory(factory);
        cbFlavor1.setButtonCell(factory.call(null));
        cbFlavor2.setCellFactory(factory);
        cbFlavor2.setButtonCell(factory.call(null));
    }

    @FXML
    public void setCurrentBorder() {
        tempPizza.setBorder(cbPizzaBorder.getValue());
        calculatePizzaItemPrice();
    }


    @FXML
    public void currentFlavor1() {
        if (cbFlavor1.getValue() != null) {
            PizzaFlavor flavor;
            flavor = cbFlavor1.getValue();
            tempPizza.setFlavor1(flavor);
            System.out.println(flavor);
            onPizzaSizeRadioButtonAction();
        }
    }

    @FXML
    public void currentFlavor2() {
        if (cbFlavor2.getValue() != null) {
            PizzaFlavor flavor;
            flavor = cbFlavor2.getValue();
            tempPizza.setFlavor2(flavor);
            System.out.println(flavor);
            onPizzaSizeRadioButtonAction();
        }
    }

    @FXML
    public void currentAdditional() {
        String currentText = tfAdditional.getText();
        PizzaAdditional additional;
        additional = cbAdditional.getValue();
        additionalPrices.add(additional.getPrice());
        additionalList.add(additional);
        System.out.println(additional);
        tfAdditional.setText(currentText + " " + additional.getName() + "; ");
        calculatePizzaPrice();
        calculatePizzaItemPrice();
    }

    @FXML
    public void addItemToOrder() {
        finalPizza = PizzaFactory.createPizza(tempPizza.getFlavor1(), tempPizza.getFlavor2(), additionalList, cbPizzaBorder.getValue(), tempPizza.getPrice(), tempPizza.getSize());
        pizzaItem.setPizzaType(finalPizza);
        pizzaItem.setPizzaQuantity(Integer.parseInt(tfPizzaQty.getText()));
        PizzaItem finalPizzaItem = new PizzaItem();
        finalPizzaItem.setPizzaType((Pizza) pizzaItem.getPizzaType());
        finalPizzaItem.setPizzaQuantity(pizzaItem.getPizzaQuantity());
        finalPizzaItem.setStatus(PizzaItemStatus.WAITING);
        pizzaOrder.getItems().add(finalPizzaItem);
        pizzaOrder.setId(new Random().nextInt(1000 - 1) + 1000);
        pizzaOrder.setClient(tfClient.getText());
        showOrderList();
        System.out.println(pizzaOrder.getItems());
        addToHistoric();
        resetForm();
    }

    @FXML
    public void addToHistoric() {
        System.out.println(pizzaItem);
        String text = "CLIENTE: " + tfClient.getText().toUpperCase() + "\n";
        for (PizzaItem item : pizzaOrder.getItems()) {
            text += item.toString() + "\n\n";
        }
        taHistoric.setText(text + "\n\n");
        updateTotal();
    }

    @FXML
    public void showOrderList() {
        System.out.println(" =========" + pizzaOrder.getItems() + "===========");
        System.out.println(" =========TOTAL: " + pizzaOrder.getTotal() + "===========");
    }

    @FXML
    public void closeOrder(){
        Main.addPizzaOrder(pizzaOrder);
        Alerts.showAlert("Pedido adicionado com sucesso!", null, "Pedido Adicionado", Alert.AlertType.INFORMATION);
        clearDescription();
        clearTotal();
    }

    private void clearDescription(){
        taHistoric.clear();
    }

    private void clearTotal(){
        lbOrderTotal.setText("R$: 0,00");
    }


    private void updateTotal() {
        lbOrderTotal.setText("R$ " + String.format("%.2f", pizzaOrder.getTotal()));
    }

    private Boolean isFormValid() {
        Boolean hasBorder = cbPizzaBorder.getValue() != null;
        Boolean hasFlavor1 = cbFlavor1.getValue() != null;
        Boolean hasQuantity = Integer.parseInt(tfPizzaQty.getText()) > 0;
        Boolean hasClient = !tfClient.getText().isBlank();

        return hasBorder && hasFlavor1 && hasQuantity && hasClient;
    }


    @FXML
    public void resetForm() {
        tfClient.setText("");
        cbFlavor1.valueProperty().setValue(null);
        cbFlavor2.valueProperty().setValue(null);
        cbPizzaBorder.setValue(null);
        tfPizzaQty.setText("1");
        clearAdditionalTextField();
        additionalList.clear();
        additionalPrices.clear();
        lbItemSubtotal.setText("R$ 0,00");
        disableOrderButtons();
    }

    private void clearAdditionalTextField() {
        tfAdditional.clear();
    }

    private void disableOrderButtons() {
        btAddItem.setDisable(!isFormValid());
        if (pizzaOrder.getItems().size() > 0) {
            btCloseOrder.setDisable(false);
        } else {
            btCloseOrder.setDisable(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tempPizza = new Pizza();
        tempPizza.setSize(radioPizzaSizeChange());
        pizzaItem = new PizzaItem();
        pizzaOrder = new PizzaOrder();
        pizzaOrder.setStatus(PizzaOrderStatus.WAITING);
        fillFlavorsList();
        fillAdditionalList();
        fillBordersList();
        additionalList = new ArrayList<>();

        Constraints.setTextFieldInt(tfPizzaQty);
    }
}
