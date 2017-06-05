/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Productos.InsertarProducto;

import MODELO.Alertas;
import MODELO.Listas.ListaCatalogo;
import MODELO.Listas.ListaProductos;
import MODELO.Producto;
import VISTA.Tienda.Productos.ProductosController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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
public class InsertarProductoController implements Initializable {

    int idTienda;
    ListaProductos lp;
    boolean aceptar = false;

    @FXML
    private ListView<Producto> tableviewProductos;
    @FXML
    private ListView<Producto> tableviewCatalogo;
    @FXML
    private Button buttonAgregar;
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
        this.tableviewCatalogo.setItems(FXCollections.observableArrayList(lc.catalogo()));
        this.tableviewProductos.setItems(FXCollections.observableArrayList(lp.getProductos()));
    }

    public void setDatos(int idTienda, ListaProductos lp) {
        this.lp = lp;
        this.idTienda = idTienda;
    }

    @FXML
    private void accionAgregar(ActionEvent event) {
        ObservableList<Producto> lista = this.tableviewCatalogo.getSelectionModel().getSelectedItems();
        Set<String> errores = new HashSet<>();

        for (Producto producto : lista) {
            try {
                lp.nuevoProductoEnTienda(idTienda, producto);
                this.tableviewProductos.getItems().add(producto);
            } catch (SQLException ex) {
                errores.add(producto.getNombre());
            }
        }

        if (!errores.isEmpty()) {
            Alertas.generarAlerta("Productos", "Información repetida", "Los productos:" + errores + " no se han añadido porque ya estaban en esa tienda", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        ObservableList<Producto> lista = this.tableviewProductos.getSelectionModel().getSelectedItems();
        if (!lista.isEmpty()) {

            Set<String> errores = new HashSet<>();

            for (Producto producto : lista) {
                try {
                    lp.eliminarProductoEnTienda(idTienda, producto);
                    this.tableviewProductos.getItems().remove(producto);
                } catch (SQLException ex) {
                    errores.add(producto.getNombre());
                }
            }

            if (!errores.isEmpty()) {
                Alertas.generarAlerta("Productos", "Información repetida", "Los productos:" + errores + " no se han podido eliminar", Alert.AlertType.INFORMATION);
            }
        }
    }

    public ListaProductos cogerLista() {
        return this.lp;
    }




}
