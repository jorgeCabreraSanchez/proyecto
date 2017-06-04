/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Perfil;

import DATOS.GestionTrabajadores;
import MODELO.Alertas;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Tienda.Which.TiendaWhichController;
import VISTA.Perfil.Configuracion.PerfilConfiguracionController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class PerfilController implements Initializable {

    Trabajadores trabajador;
    GestionTrabajadores gs;
    @FXML
    private AnchorPane barra;
    @FXML
    private Button botonIncidencias;
    @FXML
    private Button botonTienda;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gs = new GestionTrabajadores();
    }

    public void modoJefe(Trabajadores trabajador) {
        this.trabajador = trabajador;
        this.botonCatalogo.setVisible(true);
        this.botonTiendas.setVisible(true);
        this.textHorario.setVisible(false);
        this.textIDTienda.setVisible(false);
        this.menu.setMaxHeight(56);
        this.barra.setMaxHeight(56);
        this.textNombre.setLayoutY(19);
        this.textPuesto.setLayoutY(19);
        this.textPuesto.setText("Puesto: Jefe");
        todos();
    }

    public void modoTrabajador(Trabajadores trabajador) {
        this.trabajador = trabajador;
        this.botonIncidencias.setVisible(true);
        this.botonTienda.setVisible(true);
        datosTrabajador();
        todos();
    }

    private void datosTrabajador() {
        String puesto;
        String idTienda;
        if (trabajador instanceof Encargado) {
            puesto = "Encargado";
            Encargado encargado = (Encargado) trabajador;
            idTienda = String.valueOf(encargado.getIdTienda());
        } else {
            puesto = "Empleado";
            Empleado empleado = (Empleado) trabajador;
            idTienda = String.valueOf(empleado.getIdTienda());
        }
        this.textPuesto.setText("Puesto: " + puesto);
        this.textHorario.setText("Horario: " + trabajador.getHorario());
        this.textIDTienda.setText("IDTienda Nº" + idTienda);

    }

    private void todos() {
        try {
            this.textNombre.setText("Nombre:  " + trabajador.getNombre() + " " + trabajador.getApellido1() + " " + apellido2(trabajador));
            Image imagen = this.trabajador.getImagen();
            this.menu.setStyle("-fx-background-image: url(imagen)");
        } catch (IOException ex) {
            Alertas.generarAlerta("Archivo", "No se ha podido mostrar la imagen de perfil", Alert.AlertType.ERROR);
        }
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

    private String apellido2(Trabajadores trabajador) {
        String devolver = "";
        String apellido2 = trabajador.getApellido2();
        if (apellido2 != null) {
            devolver = apellido2;
        }
        return devolver;
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        try {
            String tipo;
            if (this.trabajador instanceof Jefe) {
                tipo = "Jefe";
            } else if (this.trabajador instanceof Encargado) {
                tipo = "Encargado";
            } else {
                tipo = "Empleado";
            }
            gs.desconectar(this.trabajador.getId(), tipo);
            Stage stage = (Stage) this.barra.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido cerrar sesion", "Error: " + ex.getErrorCode() + " " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionIncidencias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Incidencias/Incidencias.fxml"));
            Parent root = loader.load();
            PerfilConfiguracionController controller = loader.getController();
            controller.traerDatos(trabajador, gs);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionTienda(ActionEvent event) {
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
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionTiendas(ActionEvent event) {
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
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accionCatalogo(ActionEvent event) {
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
        } catch (IOException ex) {
            Logger.getLogger(PerfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
