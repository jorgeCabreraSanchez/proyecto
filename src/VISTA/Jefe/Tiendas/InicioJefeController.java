/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Jefe.Tiendas;

import DATOS.ListaTiendas;
import MODELO.Tienda;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class InicioJefeController implements Initializable {

    ListaTiendas ls = new ListaTiendas();

    {
        ls.cargarTiendas();
    }

    ObservableList<MenuItem> ciudadesHayTienda = FXCollections.observableArrayList();
    ObservableList<MenuItem> direcciones = FXCollections.observableArrayList();
    ObservableList<Tienda> tiendas = FXCollections.observableArrayList(ls.getTiendas());

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
    private TableColumn<Tienda, Integer> columnaID;
    @FXML
    private TableColumn<Tienda, String> columnaCiudad;
    @FXML
    private TableColumn<Tienda, String> columnaDireccion;
    @FXML
    private ContextMenu menuDireccion;
    @FXML
    private TextField direccion;
    @FXML
    private Button buttonEditar;
    @FXML
    private Button buttonBorrar;
    @FXML
    private Button buttonNuevo;
    @FXML
    private Button buttonVer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tiendas();
        cerrar();
        tabla();
    }

    private void tabla() {
        this.tabla.setItems(tiendas);
        this.columnaID.setCellValueFactory(new PropertyValueFactory<>("idTienda"));
        this.columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tabla.setTableMenuButtonVisible(true);
        tabla.setPlaceholder(new Label("No se encontro ninguna tienda en esa ubicación."));

    }

    private void tiendas() {
        ciudadesHayTienda();
        direcciones();
    }

    private void ciudadesHayTienda() {
        menuCiudad.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MenuItem mn = (MenuItem) e.getTarget();
                ciudad.setText(mn.getUserData().toString());
            }
        });

        actualizarCiudades("");

        this.ciudad.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()) {
                actualizarCiudades(newValue);
                menuCiudad.show(ciudad, Side.BOTTOM, 0, 0);
                this.direccion.setDisable(false);
            } else {
                actualizarCiudades("");
                menuCiudad.hide();
                this.direccion.setDisable(true);
            }
        });

    }

    private void actualizarCiudades(String ciu) {
        tiendas.clear();
        tiendas.addAll(ls.getTiendas(ciu, ""));

        List<String> ciudadesTienda = ls.getCiudadesHayTienda();
        ciudadesHayTienda.clear();
        for (String ciudad : ciudadesTienda) {
            MenuItem mn = new MenuItem(ciudad);
            mn.setUserData(ciudad);
            ciudadesHayTienda.add(mn);
        }
        this.menuCiudad.getItems().clear();
        this.menuCiudad.getItems().addAll(this.ciudadesHayTienda);

        if (!ciu.isEmpty()) {
            List<String> direccione = ls.getDirecciones();
            direcciones.clear();
            for (String direccion : direccione) {
                MenuItem mn2 = new MenuItem(direccion);
                mn2.setUserData(direccion);
                this.direcciones.add(mn2);
            }
            this.menuDireccion.getItems().clear();
            this.menuDireccion.getItems().addAll(this.direcciones);
        }

    }

    private void direcciones() {
        this.direccion.setDisable(true);

        this.menuDireccion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem mn = (MenuItem) event.getTarget();
                direccion.setText(mn.getUserData().toString());
            }
        });

        actualizarDirecciones("");

        this.direccion.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()) {
                actualizarDirecciones(newValue);
                menuDireccion.show(direccion, Side.BOTTOM, 0, 0);
            } else {
                actualizarDirecciones("");
                menuDireccion.hide();
            }

        });
    }

    private void actualizarDirecciones(String dire) {
        String ciu = this.ciudad.getText();
        this.tiendas.clear();
        this.tiendas.addAll(ls.getTiendas(ciu, dire));

        List<String> direccione = ls.getDirecciones();
        this.direcciones.clear();
        for (String direccion : direccione) {
            MenuItem mn2 = new MenuItem(direccion);
            mn2.setUserData(direccion);
            this.direcciones.add(mn2);
        }
        this.menuDireccion.getItems().clear();
        this.menuDireccion.getItems().addAll(this.direcciones);
    }

    private void cerrar() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

                MODELO.Alertas.generarAlerta("Advertencia", "Esta seguro que desea salir de la aplicación?", Alert.AlertType.INFORMATION);
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
        desclickarContextMenu();
    }

    private void desclickarContextMenu() {
        if (this.menuCiudad.isShowing()) {
            this.menuCiudad.hide();
        } else if (this.menuDireccion.isShowing()) {
            this.menuDireccion.hide();
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) throws IOException {
        desclickarContextMenu();
        Tienda tienda = this.tabla.getSelectionModel().getSelectedItem();
        if (tienda != null) {
            ls.setTiendaEditar(tienda);
            Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Jefe/Tiendas/Editar/EditarTienda.fxml"));
            Stage stage = new Stage();
            stage.initModality((Modality.APPLICATION_MODAL));
            stage.setScene(new Scene(root));
            stage.show();
            System.out.println("viene");
            if ((boolean) stage.getUserData()) {
                actualizarCiuYDire(this.ciudad.getText(), this.direccion.getText());
                System.out.println("entra");
            }
        }
    }

    @FXML
    private void accionBorrar(ActionEvent event) {
        desclickarContextMenu();
        Tienda tienda = tabla.getSelectionModel().getSelectedItem();
        if (tienda != null) {
            Alert check = new Alert(AlertType.CONFIRMATION);
            check.setTitle("Tiendas");
            check.setHeaderText("Esta seguro que desea borra la tienda?");
            check.setContentText("Información de la tienda: \n  ID: " + tienda.getIdTienda() + "   Ciudad: " + tienda.getCiudad() + "   Dirección: " + tienda.getDireccion());
            Optional<ButtonType> boton = check.showAndWait();
            if (boton.get().getText().equalsIgnoreCase("aceptar")) {
                ls.borrarTienda(tabla.getSelectionModel().getSelectedItem());
                actualizarCiuYDire(this.ciudad.getText(), this.direccion.getText());
            }
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) throws IOException {
        desclickarContextMenu();
        Parent root = FXMLLoader.load(getClass().getResource("/VISTA/Jefe/Tiendas/Nueva/NuevaTienda.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        actualizarCiuYDire(this.ciudad.getText(), this.direccion.getText());
    }

    @FXML
    private void accionVer(ActionEvent event) {
        desclickarContextMenu();
    }

    private void actualizarCiuYDire(String ciu, String dire) {
        tiendas.clear();
        this.tiendas.addAll(ls.getTiendas(ciu, dire));

        List<String> ciudadesTienda = ls.getCiudadesHayTienda();
        ciudadesHayTienda.clear();
        for (String ciudad : ciudadesTienda) {
            MenuItem mn = new MenuItem(ciudad);
            mn.setUserData(ciudad);
            ciudadesHayTienda.add(mn);
        }
        this.menuCiudad.getItems().clear();
        this.menuCiudad.getItems().addAll(this.ciudadesHayTienda);

        List<String> direccione = ls.getDirecciones();
        this.direcciones.clear();
        for (String direccion : direccione) {
            MenuItem mn2 = new MenuItem(direccion);
            mn2.setUserData(direccion);
            this.direcciones.add(mn2);
        }
        this.menuDireccion.getItems().clear();
        this.menuDireccion.getItems().addAll(this.direcciones);
    }
}
