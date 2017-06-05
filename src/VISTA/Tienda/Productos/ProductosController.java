/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Productos;

import MODELO.Alertas;
import MODELO.Listas.ListaProductos;
import MODELO.Producto;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Perfil.PerfilController;
import VISTA.Tienda.Productos.InsertarProducto.InsertarProductoController;
import VISTA.Tienda.Which.TiendaWhichController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
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
    private Trabajadores trabajador;

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
    @FXML
    private Label labelTitulo;
    @FXML
    private AnchorPane barra;
    @FXML
    private MenuButton menu;
    @FXML
    private Button botonImprimir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void mostrarProductos() throws SQLException {
        this.labelTitulo.setText("Productos de la Tienda Nº" + this.idTienda);
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
                actualizarProductos(newValue, tamaño);
                contextMenuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);
            } else {
                actualizarProductos(newValue, "corto");
                contextMenuNombre.hide();
            }
        });

        actualizarProductos("", "corto");
    }

    private void actualizarProductos(String nombre1, String tamaño) {
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

    public void setTrabajador(Trabajadores trabajador, int idTienda) {
        this.idTienda = idTienda;
        this.trabajador = trabajador;
    }

    public void setTrabajador(Trabajadores trabajador) {
        this.trabajador = trabajador;
        if (trabajador instanceof Encargado) {
            Encargado encargado = (Encargado) trabajador;
            this.idTienda = encargado.getIdTienda();
        } else {
            Empleado empleado = (Empleado) trabajador;
            this.idTienda = empleado.getIdTienda();
        }
        this.botonNuevo.setVisible(false);
    }

    @FXML
    private void desclickar(MouseEvent event) {
        desclickar();
    }

    private void desclickar() {
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
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Productos/InsertarProducto/InsertarProducto.fxml"));
            Parent root = loader.load();
            InsertarProductoController controller = loader.getController();
            controller.setDatos(this.idTienda, this.lp);
            controller.rellenarTablas();

            Stage stageNuevo = new Stage();
            stageNuevo.initModality(Modality.APPLICATION_MODAL);
            stageNuevo.setScene(new Scene(root));
            stageNuevo.showAndWait();

            this.lp = controller.cogerLista();
            actualizarProductos(this.textfieldNombre.getText(), "corto");

            /* COger datos */
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
            if (this.trabajador instanceof Jefe) {
                controller.setTrabajador(this.idTienda, this.trabajador);
            } else {
                controller.setTrabajador(this.trabajador);
            }

            Stage stage = (Stage) this.buttonVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Ver Tienda", "No se ha podido mostrar la ventana de ver Tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void perfil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Perfil/Perfil.fxml"));
            Parent root = loader.load();
            PerfilController controller = loader.getController();
            controller.setDatos(this.trabajador);

            Stage stage = (Stage) this.tabla.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Perfil", "Ahora mismo no se puede mostrar el perfil, intentelo más tarde.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        PerfilController.cerrarSesion(this.trabajador, (Stage) this.tabla.getScene().getWindow());
    }

    @FXML
    private void desclickarPerfil(MouseEvent event) {
        desclickar();
        this.menu.show();
    }

    @FXML
    private void accionImprimir(ActionEvent event) {
        Alert ventanita = new Alert(Alert.AlertType.INFORMATION);
        ventanita.setTitle("Precios");
        ventanita.setHeaderText("Eliga el archivo donde quiere generar el ticket");
        ButtonType abrir = new ButtonType("Buscar archivo");
        ButtonType defecto = new ButtonType("Por defecto");
        ButtonType cancelar = new ButtonType("Cancelar");
        ventanita.getButtonTypes().setAll(abrir, defecto, cancelar);
        Optional<ButtonType> elegido = ventanita.showAndWait();

        if (elegido.get() == abrir) {
            DirectoryChooser elegir = new DirectoryChooser();
            Path archivo = Paths.get(new File("").getAbsolutePath() + "\\productos");
            elegir.setInitialDirectory(archivo.toFile());

            try {
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                File file = elegir.showDialog(new Stage());
                if (file != null) {
                    this.lp.generarTXT(file.toPath(), this.idTienda);
                }
            } catch (IOException ex) {
                Alertas.generarAlerta("Generar Lista Productos", "Error generando la lista de productos", "No se ha podido imprimir la lista de productos", Alert.AlertType.WARNING);
            }

        } else if (elegido.get() == cancelar) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Producto");
            alerta.setHeaderText("No se generará la lista de productos");
            alerta.setContentText("No se generará la lista de productos debido a que usted no ha puesto \n"
                    + "un archivo destino");
        } else if (elegido.get() == defecto) {
            try {
                this.lp.generarTXT(this.idTienda);
            } catch (IOException ex) {
                Alertas.generarAlerta("Generar Lista Productos", "Error generando la lista de productos", "No se ha podido imprimir la lista de productos", Alert.AlertType.WARNING);
            }
        }

    }

}
