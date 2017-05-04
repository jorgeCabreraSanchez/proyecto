/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jefe;

import Tiendas.ListaCiudadesConTienda;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.HyperlinkEvent;

public class JefeController implements Initializable {

    @FXML
    private AnchorPane fondo;
    @FXML
    private TextField ciudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private TextField dirección;
    @FXML
    private AnchorPane base;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tiendas();
        cerrar();
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

    private void tiendas() {
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

    private void cerrar(){       
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               
                Alertas.Alertas.generarAlerta("Advertencia","Esta seguro que desea salir de la aplicación?",Alert.AlertType.INFORMATION);
            }
        });
        
      
         


    }

}
