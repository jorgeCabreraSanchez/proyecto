/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jefe;

import Tiendas.ListaTiendas;
import Tiendas.Tienda;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

public class JefeController implements Initializable {

    @FXML
    private AnchorPane fondo;
    @FXML
    private TextField ciudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private AnchorPane base;
    @FXML
    private TableView<Tienda> tabla;
    @FXML
    private TableColumn<Tienda, Integer> tablaID;
    @FXML
    private TableColumn<Tienda, String> tablaCiudad;
    @FXML
    private TableColumn<Tienda, String> tablaDireccion;
    @FXML
    private ContextMenu menuDireccion;
    @FXML
    private TextField direccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tiendas();
        cerrar();
    }


    @FXML
    private void elegirCiudadMenu(ActionEvent event) {

    }

    private void tiendas() {
        ListaTiendas ls = new ListaTiendas();
        ciudades(ls);
        direcciones(ls);

        ls.cargarTiendas("", "ciudad","");
        this.tabla.setItems(ls.getTiendas());
        this.tablaID.setCellValueFactory(new PropertyValueFactory<>("idTienda"));
        this.tablaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        this.tablaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

    }

    private void ciudades(ListaTiendas ls) {
        menuCiudad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MenuItem mn = (MenuItem) e.getTarget();
                ciudad.setText(mn.getUserData().toString());
            }
        });

        this.menuCiudad.getItems().addAll(ls.getCiudades(""));

        this.ciudad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()) {
                this.menuCiudad.getItems().clear();
                this.menuCiudad.getItems().addAll(ls.getCiudades(newValue));
                this.menuDireccion.getItems().clear();
                this.menuDireccion.getItems().addAll(ls.getDirecciones());
                menuCiudad.show(ciudad, Side.BOTTOM, 0, 0);
                this.direccion.setDisable(false);
            } else {
                this.menuCiudad.getItems().clear();
                this.menuCiudad.getItems().addAll(ls.getCiudades(""));
                menuCiudad.hide();
                this.direccion.setDisable(true);
            }
        });

    }

    private void direcciones(ListaTiendas ls) {
        this.direccion.setDisable(true);

        this.menuDireccion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem mn = (MenuItem) event.getTarget();
                direccion.setText(mn.getUserData().toString());
            }
        });

        String ciudad = this.ciudad.getText();
        this.menuDireccion.getItems().addAll(ls.getDirecciones("",ciudad));

        this.direccion.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()) {
                this.menuDireccion.getItems().clear();
                this.menuDireccion.getItems().addAll(ls.getDirecciones(newValue,ciudad));
                menuDireccion.show(direccion, Side.BOTTOM, 0, 0);
            } else {
                this.menuDireccion.getItems().clear();
                this.menuDireccion.getItems().addAll(ls.getDirecciones("",ciudad));
                menuDireccion.hide();
            }

        });

    }

    private void cerrar() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                Alertas.Alertas.generarAlerta("Advertencia", "Esta seguro que desea salir de la aplicación?", Alert.AlertType.INFORMATION);
            }
        });

    }

    @FXML
    private void ciudadesClick(MouseEvent event) {
        if (this.menuCiudad.isShowing()) {
            this.menuCiudad.hide();
        } else {
            menuCiudad.show(ciudad, Side.BOTTOM, 0, 0);
        }
        if (this.menuDireccion.isShowing()) {
            this.menuDireccion.hide();
        }
    }

    @FXML
    private void direccionClick(MouseEvent event) {
        if (this.menuDireccion.isShowing()) {
            this.menuDireccion.hide();
        } else {
            menuDireccion.show(direccion, Side.BOTTOM, 0, 0);
        }
        if (this.menuCiudad.isShowing()) {
            this.menuCiudad.hide();
        }
    }

    @FXML
    private void nadaClick(MouseEvent event) {
        if (this.menuCiudad.isShowing()) {
            this.menuCiudad.hide();
        } else if (this.menuDireccion.isShowing()) {
            this.menuDireccion.hide();
        }
    }

}
