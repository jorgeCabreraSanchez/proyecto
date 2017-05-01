/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alertas;

import javafx.scene.control.Alert;

/**
 *
 * @author Jorge Cabrera
 */
public class Alertas {

    static public void generarAlerta(String header, String content, Alert.AlertType alert) {
        Alert alerta = new Alert(alert);
        alerta.setHeaderText(header);
        alerta.setContentText(content);
        alerta.showAndWait();
    }

    static public void generarAlerta(String header, Alert.AlertType alert) {
        Alert alerta = new Alert(alert);
        alerta.setHeaderText(header);
        alerta.showAndWait();
    }

}
