/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Perfil.Configuracion;

import DATOS.GestionTrabajadores;
import MODELO.Alertas;
import MODELO.Trabajadores.Trabajadores;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class PerfilConfiguracionController implements Initializable {

    GestionTrabajadores gs;
    Trabajadores trabajador;

    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApellido1;
    @FXML
    private TextField textFieldApellido2;
    @FXML
    private TextField textFieldContraseña;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonCancelar;
    @FXML
    private PasswordField contraseñaField;
    @FXML
    private CheckBox checkboxContraseña;
    @FXML
    private ImageView imagen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void traerDatos(Trabajadores trabajador, GestionTrabajadores gs) {
        this.trabajador = trabajador;
        this.textFieldNombre.setText(trabajador.getNombre());
        this.textFieldApellido1.setText(trabajador.getApellido1());
        this.textFieldApellido2.setText(trabajador.getApellido2());
        this.contraseñaField.setText(trabajador.getContraseña());
//        this.imagen.setImage(value);
        this.gs = gs;

        this.textFieldContraseña.setManaged(false);
        this.textFieldContraseña.setVisible(false);

        this.textFieldContraseña.managedProperty().bind(this.checkboxContraseña.selectedProperty());
        this.textFieldContraseña.visibleProperty().bind(this.checkboxContraseña.selectedProperty());

        this.contraseñaField.managedProperty().bind(this.checkboxContraseña.selectedProperty().not());
        this.contraseñaField.visibleProperty().bind(this.checkboxContraseña.selectedProperty().not());

        // Bind the textField and passwordField text values bidirectionally.
        this.textFieldContraseña.textProperty().bindBidirectional(this.contraseñaField.textProperty());
    }

    @FXML
    private void accionModificar(ActionEvent event) {
        if (verificar()) {
            try {
                String nombre = this.textFieldNombre.getText();    
                String apellido1 = this.textFieldApellido1.getText();
                String apellido2 = this.textFieldApellido2.getText();
                String contraseña = this.textFieldContraseña.getText();
                if(this.trabajador.getNombre().equalsIgnoreCase(nombre)){
                    Alertas.generarAlerta("Modificación Trabajador", "No se ha modificado ningún dato", Alert.AlertType.INFORMATION);
                } else {                                    
                this.trabajador.setNombre(nombre);
                this.trabajador.setApellido1(apellido1);
                this.trabajador.setApellido2(apellido2);
                this.trabajador.setContraseña(contraseña);

                gs.modificarPerfil(this.trabajador);
                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                stage.close();
                }
                
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se han podido modificar los datos", "Error: " + ex.getErrorCode() + " " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    
    public Trabajadores cogerTrabajador(){
        return this.trabajador;
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    private boolean verificar() {
        boolean devolver = true;
        if (this.textFieldNombre.getText().isEmpty()) {
            devolver = false;
            this.textFieldNombre.setStyle("-fx-border-color:red");
        } else {
            this.textFieldNombre.setStyle("-fx-border-color:none");
        }
        if (this.textFieldApellido1.getText().isEmpty()) {
            devolver = false;
            this.textFieldApellido1.setStyle("-fx-border-color:red");
        } else {
            this.textFieldNombre.setStyle("-fx-border-color:none");
        }
        if (this.textFieldContraseña.getText().isEmpty() || this.contraseñaField.getText().isEmpty()) {
            devolver = false;
            this.textFieldContraseña.setStyle("-fx-border-color:red");
            this.contraseñaField.setStyle("-fx-border-color:red");
        } else {
            this.textFieldNombre.setStyle("-fx-border-color:none");
            this.contraseñaField.setStyle("-fx-border-color:none");
        }
        return devolver;
    }
}
