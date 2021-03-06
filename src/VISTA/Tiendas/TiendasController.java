/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tiendas;
//

import MODELO.Alertas;
import MODELO.Listas.ListaTiendas;
import MODELO.Tienda;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Perfil.PerfilController;
import VISTA.Tienda.Which.TiendaWhichController;
import VISTA.Tiendas.Editar.EditarTiendaController;
import VISTA.Tiendas.Nueva.NuevaTiendaController;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class TiendasController implements Initializable {

    ListaTiendas lt;
    Trabajadores trabajador;

    ObservableList<MenuItem> ciudadesHayTienda;
    ObservableList<MenuItem> direcciones;
    ObservableList<Tienda> tiendas;

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
    @FXML
    private AnchorPane fondito;
    @FXML
    private ImageView imagen;
    @FXML
    private MenuButton menu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            lt = new ListaTiendas();
        } catch (SQLException ex) {
            Alertas.generarAlerta("Error BD", "Ha habido un error intentando traer las tiendas de la BD", AlertType.ERROR);
        }
        ciudadesHayTienda = FXCollections.observableArrayList();
        direcciones = FXCollections.observableArrayList();
        tiendas = FXCollections.observableArrayList(lt.getListaTiendasMostrar());

        tiendas();
        cerrar();
        tabla();
    }

    private void tabla() {
        this.tabla.setItems(tiendas);
        this.columnaID.setCellValueFactory(new PropertyValueFactory<>("idTienda"));
        this.columnaCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        columnaID.sortTypeProperty().set(TableColumn.SortType.ASCENDING);

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
                this.direccion.clear();
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
        tiendas.addAll(lt.getListaTiendasMostrar(ciu, ""));

        Set<String> ciudadesTienda = lt.getCiudades();
        ciudadesHayTienda.clear();
        for (String ciudad : ciudadesTienda) {
            MenuItem mn = new MenuItem(ciudad);
            mn.setUserData(ciudad);
            ciudadesHayTienda.add(mn);
        }
        this.menuCiudad.getItems().clear();
        this.menuCiudad.getItems().addAll(this.ciudadesHayTienda);

        if (!ciu.isEmpty()) {
            Set<String> direccione = lt.getDirecciones();
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
        this.tiendas.addAll(lt.getListaTiendasMostrar(ciu, dire));

        Set<String> direccione = lt.getDirecciones();
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
    private void desclickar() {
        desclickarContextMenu();
        this.menu.show();
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        desclickarContextMenu();
        Tienda tiendaAntigua = this.tabla.getSelectionModel().getSelectedItem();
        if (tiendaAntigua != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/Tiendas/Editar/EditarTienda.fxml"));
                Parent root = loader.load();
                EditarTiendaController controller = loader.getController();
                controller.setTiendaAntigua(tiendaAntigua);
                controller.setLt(lt);
                controller.mostrarTienda();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                if (controller.getBoton().equalsIgnoreCase("editar")) {
                    actualizarCiuYDire(this.ciudad.getText(), this.direccion.getText());
                }
            } catch (IOException ex) {
                Alertas.generarAlerta("Ventanas", "No se ha podido abrir la edición de la tienda elegida, lo sentimos", AlertType.ERROR);
            }
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        try {
            desclickarContextMenu();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tiendas/Nueva/NuevaTienda.fxml"));
            Parent root = loader.load();
            NuevaTiendaController controller = loader.getController();
            controller.setLt(lt);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (controller.getBoton().equalsIgnoreCase("nuevo")) {
                actualizarCiuYDire(this.ciudad.getText(), this.direccion.getText());
            }
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventanas", "No se ha podido abrir la creacion de tiendas, lo sentimos", AlertType.ERROR);
        }
    }

    @FXML
    private void accionBorrar(ActionEvent event) {
        desclickarContextMenu();
        Tienda tienda = tabla.getSelectionModel().getSelectedItem();
        if (tienda != null) {
            Optional<ButtonType> boton = Alertas.generarAlerta("Tiendas", "Esta seguro que desea borra la tienda?", "Información de la tienda: \n  ID: " + tienda.getIdTienda() + "   Ciudad: " + tienda.getCiudad() + "   Dirección: " + tienda.getDireccion(), AlertType.CONFIRMATION);
            if (boton.get().getText().equalsIgnoreCase("aceptar")) {
                try {
                    lt.borrarTienda(tabla.getSelectionModel().getSelectedItem());
                    actualizarCiuYDire(this.ciudad.getText(), this.direccion.getText());
                } catch (SQLException e) {
                    Alertas.generarAlerta("Error BD", "Ha habido un error intentando borrar la tienda y no se ha podido", "Compruebe que la tienda no contenga trabajadores, de lo contrario no le dejara borrarla.", AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    private void accionVer(ActionEvent event) {
        try {
            desclickarContextMenu();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Which/TiendaWhich.fxml"));
            Parent root = loader.load();
            TiendaWhichController controller = loader.getController();

            if (this.tabla.getSelectionModel().getSelectedItem() != null) {
                controller.setTrabajador(this.tabla.getSelectionModel().getSelectedItem().getIdTienda(), this.trabajador);

                Stage stage = (Stage) this.buttonNuevo.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            }
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventanas", "No se ha podido visualizar la tienda elegida, lo sentimos", ex.getLocalizedMessage(), AlertType.ERROR);
        }

    }

    private void actualizarCiuYDire(String ciu, String dire) {
        tiendas.clear();
        this.tiendas.addAll(lt.getListaTiendasMostrar(ciu, dire));

        Set<String> ciudadesTienda = lt.getCiudades();
        ciudadesHayTienda.clear();
        for (String ciudad : ciudadesTienda) {
            MenuItem mn = new MenuItem(ciudad);
            mn.setUserData(ciudad);
            ciudadesHayTienda.add(mn);
        }
        this.menuCiudad.getItems().clear();
        this.menuCiudad.getItems().addAll(this.ciudadesHayTienda);

        Set<String> direccione = lt.getDirecciones();
        this.direcciones.clear();
        for (String direccion : direccione) {
            MenuItem mn2 = new MenuItem(direccion);
            mn2.setUserData(direccion);
            this.direcciones.add(mn2);
        }
        this.menuDireccion.getItems().clear();
        this.menuDireccion.getItems().addAll(this.direcciones);
    }

    public void setTrabajador(Trabajadores trabajador) {
        this.trabajador = trabajador;
    }

    @FXML
    private void perfil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Perfil/Perfil.fxml"));
            Parent root = loader.load();
            PerfilController controller = loader.getController();
            controller.setDatos(this.trabajador);

            Stage stage = (Stage) this.base.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Perfil", "Ahora mismo no se puede mostrar el perfil, intentelo más tarde.", AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        PerfilController.cerrarSesion(this.trabajador, (Stage) this.base.getScene().getWindow());
    }
}
