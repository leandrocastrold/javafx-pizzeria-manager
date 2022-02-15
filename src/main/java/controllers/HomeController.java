package controllers;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.ERROR;

public class HomeController implements Initializable {

    @FXML
    private Tab tabKitchen;
    @FXML
    private Tab tabForm;
    @FXML
    private Tab tabRegister;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabHome;

    public synchronized void loadView(String absolutePath) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absolutePath));
            HBox hBox = loader.load();
            Scene mainScene = Main.getMainScene();
            AnchorPane root = ((AnchorPane) mainScene.getRoot());
            VBox vBoxPlaceHolder = (VBox) ((VBox) root.getChildren().get(0)).getChildren().get(3);
            vBoxPlaceHolder.getChildren().clear();
            vBoxPlaceHolder.getChildren().add(hBox);
        } catch (IOException e) {
            Alerts.showAlert("Erro ao carregar View :" + absolutePath, null, "Erro", ERROR);
        }
    }

    private void disableTabHome(){
      tabHome.setDisable(true);
    }

    @FXML
    public void onTabRegisterAction() {

        if (Main.getMainScene() != null) {
            disableTabHome();
            loadView("/views/Register.fxml");
        }
    }

    @FXML
    public void onTabKitchenAction() {
        disableTabHome();
        loadView("/views/Kitchen.fxml");
    }

    @FXML
    public void onTabFormAction() {
        disableTabHome();
        loadView("/views/PizzaForm.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    onTabRegisterAction();
    }
}
