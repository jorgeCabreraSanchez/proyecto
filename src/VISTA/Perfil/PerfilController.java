/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Perfil;

import DATOS.GestionTrabajadores;
import MODELO.Alertas;
import MODELO.Incidencia.IncidenciaTrabajador;
import MODELO.Listas.ListaIncidenciasTrabajador;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Catalogo.CatalogoController;
import VISTA.Tienda.Which.TiendaWhichController;
import VISTA.Perfil.Configuracion.PerfilConfiguracionController;
import VISTA.Tiendas.TiendasController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDateTextField;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class PerfilController implements Initializable {

    Trabajadores trabajador;
    GestionTrabajadores gs;
    ListaIncidenciasTrabajador lit;
    @FXML
    private AnchorPane barra;
    @FXML
    private Button botonTiendas;
    @FXML
    private Button botonCatalogo;
    @FXML
    private Label textNombre;
    @FXML
    private Label textPuesto;
    @FXML
    private Label textHorario;
    @FXML
    private Label textIDTienda;
    @FXML
    private MenuButton menu;
    @FXML
    private ListView<IncidenciaTrabajador> listViewIncidencias;
    @FXML
    private LocalDateTextField datePickerHasta;
    @FXML
    private LocalDateTextField datePickerDesde;
    @FXML
    private MenuItem menuTienda;
    @FXML
    private Text label1;
    @FXML
    private Text label2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gs = new GestionTrabajadores();
    }

    public void setDatos(Trabajadores trabajador) {
        if (trabajador instanceof Jefe) {
            this.trabajador = trabajador;
            this.botonCatalogo.setVisible(true);
            this.botonTiendas.setVisible(true);
            this.textHorario.setVisible(false);
            this.textHorario.setManaged(false);
            this.textIDTienda.setVisible(false);
            this.textIDTienda.setManaged(false);
            this.label1.setVisible(false);
            this.label1.setManaged(false);
            this.label2.setVisible(false);
            this.label2.setManaged(false);
            this.listViewIncidencias.setVisible(false);
            this.listViewIncidencias.setManaged(false);
            this.datePickerDesde.setVisible(false);
            this.datePickerDesde.setManaged(false);
            this.datePickerHasta.setVisible(false);
            this.datePickerHasta.setVisible(false);
            this.menu.setMaxHeight(51);
            this.barra.setMaxHeight(51);
            this.textNombre.setLayoutY(16);
            this.textPuesto.setLayoutY(16);
            this.textPuesto.setText("Puesto: Jefe");
            this.menuTienda.setVisible(false);
        } else {
            this.trabajador = trabajador;
            datosTrabajador();
            incidencias();
        }

        todos();
    }

    private void incidencias() {
        try {
            this.datePickerHasta.setLocalDate(LocalDate.now());
            this.lit = new ListaIncidenciasTrabajador(this.trabajador.getId());
            actualizarIncidencias();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            this.datePickerDesde.setDateTimeFormatter(formato);
            this.datePickerHasta.setDateTimeFormatter(formato);             
            this.datePickerDesde.localDateProperty().addListener((observable, oldValue, newValue) -> {                
                    actualizarIncidencias();                
            });
             this.datePickerHasta.localDateProperty().addListener((observable, oldValue, newValue) -> {                
                    actualizarIncidencias();                
            });

        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se han podido traer las incidencias del usuario", Alert.AlertType.ERROR);
        }
    }

    private void actualizarIncidencias() {
        LocalDate fechaEntrada;
        LocalDate fechaSalida;
        if (this.datePickerDesde.getLocalDate() == null) {
            fechaEntrada = LocalDate.MIN;
        } else {
            fechaEntrada = this.datePickerDesde.getLocalDate();
        }
        if (this.datePickerHasta.getLocalDate() == null) {
            fechaSalida = LocalDate.now();
        } else {
            fechaSalida = this.datePickerHasta.getLocalDate();

        }
        this.listViewIncidencias.getItems().clear();
        this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias(fechaEntrada, fechaSalida)));
    }

    private void datosTrabajador() {
        String puesto;
        String idTienda;
        if (this.trabajador instanceof Encargado) {
            puesto = "Encargado";
            Encargado encargado = (Encargado) this.trabajador;
            idTienda = String.valueOf(encargado.getIdTienda());
        } else {
            puesto = "Empleado";
            Empleado empleado = (Empleado) this.trabajador;
            idTienda = String.valueOf(empleado.getIdTienda());
        }
        this.textPuesto.setText("Puesto: " + puesto);
        this.textHorario.setText("Horario: " + this.trabajador.getHorario());
        this.textIDTienda.setText("IDTienda Nº" + idTienda);

    }

    private void todos() {
//        try {
        this.textNombre.setText("Nombre:  " + trabajador.getNombre() + " " + trabajador.getApellido1() + " " + apellido2(trabajador.getApellido2()));
//            Image imagen = this.trabajador.getImagen();
//            this.menu.setStyle("-fx-background-image: url(imagen)");
//        } catch (IOException ex) {
//            Alertas.generarAlerta("Archivo", "No se ha podido mostrar la imagen de perfil", Alert.AlertType.ERROR);
//        }
    }

    @FXML
    private void configuracion() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Perfil/Configuracion/PerfilConfiguracion.fxml"));
            Parent root = loader.load();
            PerfilConfiguracionController controller = loader.getController();
            controller.traerDatos(trabajador, gs);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            this.trabajador = controller.cogerTrabajador();
            if (trabajador instanceof Jefe) {
                todos();
            } else {
                todos();
                datosTrabajador();
            }

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana configurar Datos", "No se ha podido abrir la ventana de configuración de Datos", "Mensaje de error: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private String apellido2(String apellido2) {
        String devolver = "";
        if (apellido2 != null) {
            devolver = apellido2;
        }
        return devolver;
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        cerrarSesion(this.trabajador, (Stage) this.barra.getScene().getWindow());
    }

    public static void cerrarSesion(Trabajadores trabajador, Stage stage) {
        try {
            String tipo;
            if (trabajador instanceof Jefe) {
                tipo = "Jefe";
            } else if (trabajador instanceof Encargado) {
                tipo = "Encargado";
            } else {
                tipo = "Empleado";
            }
            GestionTrabajadores.desconectar(trabajador.getId(), tipo);
            stage.close();
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido cerrar sesion", "Error: " + ex.getErrorCode() + " " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionTienda(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Which/TiendaWhich.fxml"));
            Parent root = loader.load();
            TiendaWhichController controller = loader.getController();
            controller.setTrabajador(this.trabajador);

            Stage stage = (Stage) this.barra.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Which", "No se puede visualizar la tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionTiendas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tiendas/Tiendas.fxml"));
            Parent root = loader.load();
            TiendasController controller = loader.getController();
            controller.setTrabajador(this.trabajador);

            Stage stage = (Stage) this.barra.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Tiendas", "No se han podido visualizar las tiendas", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionCatalogo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Catalogo/Catalogo.fxml"));
            Parent root = loader.load();
            CatalogoController controller = loader.getController();
            controller.setTrabajador(this.trabajador);

            Stage stage = (Stage) this.barra.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Catálogo", "Ha ocurrido un error y no se ha podido abrir el catalogo", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "Ha ocurrido un error y no se ha podido abrir el catalogo", "Error: " + ex.getErrorCode() + " " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

  

}
