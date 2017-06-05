/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Which;

import MODELO.Alertas;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Perfil.PerfilController;
import VISTA.Tienda.Productos.ProductosController;
import VISTA.Tienda.Incidencias.IncidenciasController;
import VISTA.Tienda.Trabajadores.TrabajadoresController;
import VISTA.Tiendas.TiendasController;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class TiendaWhichController implements Initializable {

    private int idTienda;
    Trabajadores trabajador;

    @FXML
    private Button buttonProductos;
    @FXML
    private Button buttonTrabajadores;
    @FXML
    private Button buttonVolver;
    @FXML
    private Button buttonIncidencias;
    @FXML
    private Label labelTienda;
    @FXML
    private AnchorPane barra;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void abrirProductos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Productos/Productos.fxml"));
            Parent root = loader.load();

            ProductosController controller = loader.getController();
            if (this.trabajador instanceof Jefe) {
                controller.setTrabajador(this.trabajador, this.idTienda);
            } else {
                controller.setTrabajador(this.trabajador);
            }
            controller.mostrarProductos();

            Stage stage = (Stage) this.buttonProductos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana", "No se puede visualizar los productos de esta tienda", ex.getLocalizedMessage(), Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido visualizar los productos de esta tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void abrirTrabajadores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Trabajadores/Trabajadores.fxml"));
            Parent root = loader.load();
            TrabajadoresController controller = loader.getController();
            if (this.trabajador instanceof Jefe) {
                controller.setTrabajador(this.trabajador, this.idTienda);
            } else {
                controller.setTrabajador(this.trabajador);
            }

            try {
                controller.mostrarTrabajadores();

                Stage stage = (Stage) this.buttonProductos.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se puede visualizar los trabajadores", "Error code: " + String.valueOf(ex.getErrorCode()) + "\n Message: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajadores", "No se puede visualizar los trabajadores de esta tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tiendas/Tiendas.fxml"));
            Parent root = loader.load();
            TiendasController controller = loader.getController();
            controller.setTrabajador(this.trabajador);

            Stage stage = (Stage) this.buttonProductos.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajadores", "No se puede visualizar los trabajadores de esta tienda", Alert.AlertType.ERROR);
        }
    }

    public int getIDTienda() {
        return this.idTienda;
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
        this.labelTienda.setText(this.labelTienda.getText() + this.idTienda);
        this.buttonVolver.setVisible(false);
    }

    public void setTrabajador(int idTienda, Trabajadores trabajador) {
        this.trabajador = trabajador;
        this.idTienda = idTienda;
        this.labelTienda.setText(this.labelTienda.getText() + idTienda);
    }

    @FXML
    private void abrirIncidencias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Incidencias/Incidencias.fxml"));
            Parent root = loader.load();
            IncidenciasController controller = loader.getController();
            if (this.trabajador instanceof Jefe) {
                controller.setTrabajador(this.trabajador,this.idTienda);
            } else {
                controller.setTrabajador(this.trabajador);
            }
            controller.rellenarTabla();

            Stage stage = (Stage) this.buttonVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Incidencias", "No se ha podido abrir la ventana incidencias", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido visualizar las incidencias de la tienda", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
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

}
