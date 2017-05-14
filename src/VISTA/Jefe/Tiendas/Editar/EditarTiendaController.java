/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Jefe.Tiendas.Editar;

import DATOS.ListaCiudades;
import DATOS.ListaTiendas;
import MODELO.Tienda;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class EditarTiendaController implements Initializable {

    Tienda tienda = ListaTiendas.getTiendaEditar();
    ListaCiudades lc = new ListaCiudades();
    ObservableList<MenuItem> ciudades = FXCollections.observableArrayList();

    @FXML
    private TextField textID;
    @FXML
    private TextField textCiudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private TextField textDireccion;
    @FXML
    private Button botonEditar;
    @FXML
    private Button botonCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ciudades();
        cargarTienda();
    }

    private void cargarTienda() {
        this.textID.setText(String.valueOf(tienda.getIdTienda()));
        this.textCiudad.setText(tienda.getCiudad());
        this.textDireccion.setText(tienda.getDireccion());
    }

    private void ciudades() {
        this.menuCiudad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MenuItem mn = (MenuItem) e.getTarget();
                textCiudad.setText(mn.getUserData().toString());
            }
        });

        lc.actualizarCiudades("");

        this.textCiudad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()) {
                actualizarCiudades(newValue);
                menuCiudad.show(this.textCiudad, Side.BOTTOM, 0, 0);
            } else {
                actualizarCiudades("");
            }
        });
    }

    private void actualizarCiudades(String ciu) {
        List<String> listaCiudades = lc.actualizarCiudades(ciu);
        for (String ciudad : listaCiudades) {
            MenuItem mn = new MenuItem(ciudad);
            mn.setUserData(ciudad);
            ciudades.add(mn);
        }

        this.menuCiudad.getItems().clear();
        this.menuCiudad.getItems().addAll(ciudades);
    }

    @FXML
    private void accionEditar(ActionEvent event) {
         
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
    }

}
