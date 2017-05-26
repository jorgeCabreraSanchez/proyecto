/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Which;

import MODELO.Alertas;
import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
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

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            cerrarVentanaActual();
        } catch (IOException ex) {
            Alertas.generarAlerta("Productos", "No se puede visualizar los productos de esta tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void abrirTrabajadores(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Trabajadores/Trabajadores.fxml"));
            Parent root = loader.load();

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            cerrarVentanaActual();
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajadores", "No se puede visualizar los trabajadores de esta tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Jefe/Inicio/InicioJefe.fxml"));
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

}
