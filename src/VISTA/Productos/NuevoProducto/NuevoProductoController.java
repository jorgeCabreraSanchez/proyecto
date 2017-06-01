/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Productos.NuevoProducto;

import MODELO.Alertas;
import MODELO.Listas.ListaCatalogo;
import MODELO.Listas.ListaProductos;
import MODELO.Producto;
import VISTA.Productos.ProductosController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class NuevoProductoController implements Initializable {

    int idTienda;
    ListaProductos lp;
    @FXML
    private ListView<Producto> tableviewProductos;
    @FXML
    private ListView<Producto> tableviewCatalogo;
    @FXML
    private Button buttonAgregar;
    @FXML
    private Button buttonVolver;
    @FXML
    private Button buttonEliminar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void rellenarTablas() throws SQLException {
        ListaCatalogo lc = new ListaCatalogo();
        this.tableviewCatalogo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableviewProductos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.tableviewProductos.setItems(FXCollections.observableArrayList(lc.catalogo()));
        this.tableviewCatalogo.setItems(FXCollections.observableArrayList(lp.getProductos()));
    }

    public void setDatos(int idTienda, ListaProductos lp) {
        this.lp = lp;
        this.idTienda = idTienda;
    }

    @FXML
    private void accionAgregar(ActionEvent event) {
        ObservableList<Producto> lista = this.tableviewProductos.getSelectionModel().getSelectedItems();
        List<String> errores = new ArrayList<>();

        for (Producto producto : lista) {
            try {
                lp.nuevoProductoEnTienda(idTienda, producto.getIdProducto());
                this.tableviewCatalogo.getItems().add(producto);
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode() + ex.getLocalizedMessage());
                errores.add(producto.getNombre());
            }
        }

        if (!errores.isEmpty()) {
            Alertas.generarAlerta("Productos", "Información repetida", "Los productos:" + errores + " no se han añadido porque ya estaban en esa tienda", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        ObservableList<Producto> lista = this.tableviewCatalogo.getSelectionModel().getSelectedItems();
        List<String> errores = new ArrayList<>();

        for (Producto producto : lista) {
            try {
                lp.eliminarProductoEnTienda(idTienda, producto.getIdProducto());
                this.tableviewCatalogo.getItems().remove(producto);
            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode() + ex.getLocalizedMessage());
                errores.add(producto.getNombre());
            }
        }

        if (!errores.isEmpty()) {
            Alertas.generarAlerta("Productos", "Información repetida", "Los productos:" + errores + " no se han podido eliminar", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Productos/Productos.fxml"));
            Parent root = loader.load();
            ProductosController controller = loader.getController();
            controller.setIDTienda(this.idTienda);
            controller.mostrarProductos();
            try {
                controller.mostrarProductos();

                Stage stageNuevo = new Stage();
                stageNuevo.setScene(new Scene(root));
                stageNuevo.show();

                Stage stage = (Stage) this.buttonAgregar.getScene().getWindow();
                stage.close();
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se puede visualizar los trabajadores", "Error code: " + String.valueOf(ex.getErrorCode()) + "\n Message: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajadores", "No se puede visualizar los trabajadores de esta tienda", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido visualizar los productos de esta tienda", Alert.AlertType.ERROR);

        }
    }

}
