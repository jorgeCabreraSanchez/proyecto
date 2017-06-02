/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Which;

import MODELO.Alertas;
import VISTA.Productos.ProductosController;
import VISTA.Tienda.Incidencias.IncidenciasController;
import VISTA.Trabajadores.TrabajadoresController;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class TiendaWhichController implements Initializable {

    private int idTienda;
    @FXML
    private Button buttonProductos;
    @FXML
    private Button buttonTrabajadores;
    @FXML
    private Button buttonVolver;
    @FXML
    private Button buttonIncidencias;

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
            loader.setLocation(getClass().getResource("/VISTA/Productos/Productos.fxml"));
            Parent root = loader.load();

            ProductosController controller = loader.getController();
            controller.setIDTienda(idTienda);
            controller.mostrarProductos();

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            cerrarVentanaActual();
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
            loader.setLocation(getClass().getResource("/VISTA/Trabajadores/Trabajadores.fxml"));
            Parent root = loader.load();
            TrabajadoresController controller = loader.getController();
            controller.setIDTienda(this.idTienda);
            try {
                controller.mostrarTrabajadores();

                Stage stageNuevo = new Stage();
                stageNuevo.setScene(new Scene(root));
                stageNuevo.show();

                cerrarVentanaActual();
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

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            cerrarVentanaActual();
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajadores", "No se puede visualizar los trabajadores de esta tienda", Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentanaActual() {
        Stage stageActual = (Stage) this.buttonProductos.getScene().getWindow();
        stageActual.close();
    }

    public int getIDTienda() {
        return this.idTienda;
    }

    public void setIDTienda(Integer idTienda) {
        this.idTienda = idTienda;
    }

    @FXML
    private void abrirIncidencias(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Incidencias/Incidencias.fxml"));
            Parent root = loader.load();
            IncidenciasController controller = loader.getController();
            controller.setIDTienda(this.idTienda);
            controller.rellenarTabla();
            
            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            Stage stage = (Stage) this.buttonVolver.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Incidencias", "No se ha podido abrir la ventana incidencias", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido visualizar las incidencias de la tienda","Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(),Alert.AlertType.ERROR);
        }
    }

}
