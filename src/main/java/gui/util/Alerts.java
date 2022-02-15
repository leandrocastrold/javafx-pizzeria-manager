package gui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

    public static void showAlert(String message, String header, String title, AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.setHeaderText(header);
        alert.setTitle(title);
        alert.show();
    }
}
