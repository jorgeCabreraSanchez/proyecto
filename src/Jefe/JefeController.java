/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jefe;

import Tiendas.ListaCiudadesConTienda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class JefeController implements Initializable {

    @FXML
    private AnchorPane fondo;
    @FXML
    private TextField ciudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private TextField dirección;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        menuCiudad.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e) {
//                ciudad.setText();
//            }
//        });

        
        this.ciudad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()) {                
                this.menuCiudad.getItems().addAll(ListaCiudadesConTienda.getCiudades());
                menuCiudad.show(ciudad, Side.BOTTOM, 0, 0);
            } else {
                menuCiudad.hide();
            }
        });
    }

    @FXML
    private void elegirCiudad(ActionEvent event) {

    }

    @FXML
    private void elegirDireccion(ActionEvent event) {
    }

    @FXML
    private void elegirCiudadMenu(ActionEvent event) {

    }

}
