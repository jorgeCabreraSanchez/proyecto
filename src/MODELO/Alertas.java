/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Jorge Cabrera
 */
public class Alertas {

    static public Optional<ButtonType> generarAlerta(String title,String header, Alert.AlertType alert) {
        Alert alerta = new Alert(alert);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        return alerta.showAndWait();
    }

     static public Optional<ButtonType> generarAlerta(String title, String header,String content, Alert.AlertType alert) {
        Alert alerta = new Alert(alert);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        return alerta.showAndWait();
    }
    
}
