/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Catalogo;

import MODELO.Alertas;
import MODELO.Listas.ListaProductos;
import MODELO.Producto;
import VISTA.Catalogo.NuevoProducto.NuevoProductoController;
import VISTA.Tienda.Which.TiendaWhichController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
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
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class ProductosController implements Initializable {

    private int idTienda;
    private ListaProductos lp;
    private ObservableList<Producto> productos;
    private ObservableList<MenuItem> menuProductos;

    @FXML
    private TableColumn<Producto, Integer> columnaidProducto;
    @FXML
    private TableColumn<Producto, String> columnaNombre;
    @FXML
    private TableColumn<Producto, String> columnaDescripcion;
    @FXML
    private TableView<Producto> tabla;
    @FXML
    private TextField textfieldNombre;
    @FXML
    private ContextMenu contextMenuNombre;
    @FXML
    private Button botonNuevo;
    @FXML
    private Button buttonVolver;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void mostrarProductos() throws SQLException {
        cargarListas();
        tabla();
    }

    private void cargarListas() throws SQLException {
        lp = new ListaProductos(this.idTienda);
        productos = FXCollections.observableArrayList();
        cargarContextMenu();
    }

    private void cargarContextMenu() {
        this.contextMenuNombre.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MenuItem mn = (MenuItem) e.getTarget();
                textfieldNombre.setText(mn.getUserData().toString());
            }
        });

        this.textfieldNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                String tamaño = "largo";
                if (oldValue.length() > newValue.length()) {
                    tamaño = "corto";
                }
                actualizarTrabajadores(newValue, tamaño);
                contextMenuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);
            } else {
                actualizarTrabajadores(newValue, "corto");
                contextMenuNombre.hide();
            }
        });

        actualizarTrabajadores("", "corto");
    }

    private void actualizarTrabajadores(String nombre1, String tamaño) {
        Set<Producto> lista = lp.getProductos(nombre1, tamaño);
        this.productos.clear();
        this.productos.addAll(lista);

        menuProductos = FXCollections.observableArrayList();

        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            String nombre = it.next().getNombre();
            MenuItem m = new MenuItem(nombre);
            m.setUserData(nombre);
            menuProductos.add(m);
        }
        this.contextMenuNombre.getItems().setAll(menuProductos);
    }

    private void tabla() {
        tabla.setPlaceholder(new Label("No se ha encontrado ningún producto."));
        tabla.setItems(productos);

        columnaidProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    public void setIDTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    @FXML
    private void desclickar(MouseEvent event) {
        if (this.contextMenuNombre.isShowing()) {
            this.contextMenuNombre.hide();
        }
    }

    @FXML
    private void clickarNombre(MouseEvent event) {
        if (this.contextMenuNombre.isShowing()) {
            this.contextMenuNombre.hide();
        } else {
            this.contextMenuNombre.show(this.textfieldNombre, Side.BOTTOM, 0, 0);
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Catalogo/NuevoProducto/NuevoProducto.fxml"));
            Parent root = loader.load();
            NuevoProductoController controller = loader.getController();
            controller.setDatos(this.idTienda,this.lp);
            controller.rellenarTablas();

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            Stage stage = (Stage) this.botonNuevo.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Añadir Producto", "No se ha podido mostrar la ventana para añadir nuevos productos", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido mostrar la ventana para añadir nuevos productos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Which/TiendaWhich.fxml"));
            Parent root = loader.load();
            TiendaWhichController controller = loader.getController();
            controller.setIDTienda(this.idTienda);

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            Stage stage = (Stage) this.botonNuevo.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Ver Tienda", "No se ha podido mostrar la ventana de ver Tienda", Alert.AlertType.ERROR);
        } 
    }

}
