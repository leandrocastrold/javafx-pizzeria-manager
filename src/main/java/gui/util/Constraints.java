package gui.util;

import javafx.scene.control.TextField;

public class Constraints {

    public static void setTextFieldInt(TextField txt){
        txt.textProperty().addListener((obs, oldValue, currentValue) -> {
        if (currentValue != null && !currentValue.matches("\\d*")) {
            txt.setText(oldValue);
        }
        });
    }

    public static void setTextFieldDouble(TextField txt) {
       txt.textProperty().addListener((obs, oldValue, currentValue) -> {
        if (currentValue != null && !currentValue.matches("\\d*([\\.]\\d*)?")){
            txt.setText(oldValue);
        }
       });
    }

    public  static void setTextFieldMaxLength(TextField txt, int max){
        txt.textProperty().addListener((obs, oldValue, currentValue) -> {
            if (currentValue != null && currentValue.length() > max){
                txt.setText(oldValue);
            }
        });
    }
}
