/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Catalogo;

import MODELO.Alertas;
import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Listas.ListaCatalogo;
import MODELO.Producto;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Catalogo.Modificar.CatalogoModificarController;
import VISTA.Perfil.PerfilController;
import VISTA.Tienda.Incidencias.NuevaEditar.NuevaEditarIncidenciaController;
import VISTA.Tienda.Which.TiendaWhichController;
import VISTA.Tiendas.TiendasController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class CatalogoController implements Initializable {

    Trabajadores trabajador;
    private ObservableList listaCatalogo = FXCollections.observableArrayList();
    ListaCatalogo lc;

    @FXML
    private Button botonNuevo;
    @FXML
    private Button botonEditar;
    @FXML
    private Button botonEliminar;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private ContextMenu contextMenuNombre;
    @FXML
    private AnchorPane barra;
    @FXML
    private MenuButton menu;
    @FXML
    private TableColumn<Producto, Integer> columnaID;
    @FXML
    private TableColumn<Producto, String> columnaNombre;
    @FXML
    private TableColumn<Producto, String> columnaDescripcion;
    @FXML
    private TableView<Producto> tablaCatalogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setTrabajador(Trabajadores trabajador) throws SQLException {
        this.trabajador = trabajador;
        lc = new ListaCatalogo();
        listaCatalogo.addAll(lc.catalogo());
        tabla();
        contextMenu();
        actualizarCatalogo("corto");
    }

    private void tabla() {
        this.tablaCatalogo.setPlaceholder(new Label("No se ha encontrado ningún trabajador."));
        this.tablaCatalogo.setItems(listaCatalogo);
        this.columnaID.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    private void contextMenu() {
        this.contextMenuNombre.setOnAction((ActionEvent e) -> {
            MenuItem mn = (MenuItem) e.getTarget();
            this.textFieldNombre.setText(mn.getUserData().toString());
        });

        this.textFieldNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || oldValue.length() > newValue.length()) {
                actualizarCatalogo("corto");
            } else {
                actualizarCatalogo("largo");
            }
            this.contextMenuNombre.show(this.textFieldNombre, Side.BOTTOM, 0, 0);

        });
    }

    private void actualizarCatalogo(String tamaño) {
        String nom = this.textFieldNombre.getText();
        Set<Producto> lista = this.lc.getProductos(nom, tamaño);
        this.listaCatalogo.clear();
        this.listaCatalogo.addAll(lista);

        ObservableList<MenuItem> listaNombre = FXCollections.observableArrayList();
        for (Producto producto : lista) {
            String nombre = producto.getNombre();
            MenuItem e = new MenuItem(nombre);
            e.setUserData(nombre);
            listaNombre.add(e);

        }
        this.contextMenuNombre.getItems().clear();
        this.contextMenuNombre.getItems().setAll(listaNombre);

    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        desclickar();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Catalogo/Modificar/CatalogoModificar.fxml"));
            Parent root = loader.load();
            CatalogoModificarController controller = loader.getController();
            controller.botonNuevo();

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.initModality(Modality.APPLICATION_MODAL);
            stageNuevo.showAndWait();

            Producto producto = controller.cogerProducto();
            if (producto != null) {
                this.listaCatalogo.clear();
                this.listaCatalogo.addAll(this.lc.nuevoProducto(producto));
                actualizarCatalogo("corto");
            }

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Nueva Incidencia", "No se ha podido abrir la ventana nueva incidencia", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido crear la incidencia", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void accionEditar(ActionEvent event) {
        desclickar();
        Producto productoDar = this.tablaCatalogo.getSelectionModel().getSelectedItem();
        if (productoDar != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/Catalogo/Modificar/CatalogoModificar.fxml"));
                Parent root = loader.load();
                CatalogoModificarController controller = loader.getController();
                controller.botonEditar(productoDar);

                Stage stageNuevo = new Stage();
                stageNuevo.setScene(new Scene(root));
                stageNuevo.initModality(Modality.APPLICATION_MODAL);
                stageNuevo.showAndWait();

                Producto producto = controller.cogerProducto();
                if (producto != null) {                    
                    this.tablaCatalogo.setItems(FXCollections.observableArrayList(this.lc.editarProducto(producto)));
                    actualizarCatalogo("corto");
                }

            } catch (IOException ex) {
                Alertas.generarAlerta("Ventana Editar Incidencia", "No se ha podido abrir la ventana editar incidencia", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se ha podido modificar la incidencia", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        desclickar();
        Producto producto = this.tablaCatalogo.getSelectionModel().getSelectedItem();

        if (producto != null) {
            Optional<ButtonType> boton = Alertas.generarAlerta("Borrado de producto", "Desea borrar el producto con la siguiente información: \n ID: " + producto.getIdProducto() + " Nombre:  " + producto.getNombre() + "  Descripción:  " + producto.getDescripcion(), Alert.AlertType.CONFIRMATION);
            if (boton.get().getText().equalsIgnoreCase("Aceptar")) {
                try {
                    lc.eliminarProducto(producto.getIdProducto());
                    actualizarCatalogo("largo");
                } catch (SQLException ex) {
                    Alertas.generarAlerta("BD", "No se ha podido borrar el producto", Alert.AlertType.ERROR);
                }
            }
        }

    }

    @FXML
    private void desclickarPerfil(MouseEvent event) {
        desclickar();
        this.menu.show();
    }

    @FXML
    private void perfil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Perfil/Perfil.fxml"));
            Parent root = loader.load();
            PerfilController controller = loader.getController();
            controller.setDatos(this.trabajador);

            Stage stage = (Stage) this.barra.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Perfil", "Ahora mismo no se puede mostrar el perfil, intentelo más tarde.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        PerfilController.cerrarSesion(this.trabajador, (Stage) this.barra.getScene().getWindow());
    }

    @FXML
    private void nombreClickado(MouseEvent event) {
        if (this.contextMenuNombre.isShowing()) {
            this.contextMenuNombre.hide();
        } else {
            this.contextMenuNombre.show(this.textFieldNombre, Side.BOTTOM, 0, 0);
        }
    }

    @FXML
    private void desclickar() {
        if (this.contextMenuNombre.isShowing()) {
            this.contextMenuNombre.hide();
        }
    }

    private void desclickar(MouseEvent event) {
        desclickar();
    }

}
