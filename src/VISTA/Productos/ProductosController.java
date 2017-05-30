/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Productos;

import MODELO.Listas.ListaProductos;
import MODELO.Producto;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void mostrarTrabajadores() throws SQLException {
        cargarListas();
        tabla();

    }

    private void cargarListas() throws SQLException {
        lp = new ListaProductos(this.idTienda);
        Set<Producto> lista = lp.getProductos();
        productos = FXCollections.observableArrayList(lista);
        cargarContextMenu(lista);
    }

    private void cargarContextMenu(Set<Producto> lista) {
        menuProductos = FXCollections.observableArrayList();
        Iterator<Producto> it = lista.iterator();
        while (it.hasNext()) {
            String nombre = it.next().getNombre();
            MenuItem m = new MenuItem(nombre);
            m.setUserData(nombre);
            menuProductos.add(m);
        }
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

}
