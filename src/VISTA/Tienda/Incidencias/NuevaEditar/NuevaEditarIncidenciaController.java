/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Incidencias.NuevaEditar;

import MODELO.Incidencia.IncidenciaTienda;
import VISTA.Tienda.Incidencias.IncidenciasController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class NuevaEditarIncidenciaController implements Initializable {
    private IncidenciaTienda incidencia;
    @FXML
    private TextField textfieldTitulo;
    @FXML
    private TextArea textfieldDescripcion;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void accionCrear(ActionEvent event) throws IOException {
        guardarIncidencia();
    }
    
    private void guardarIncidencia(){
        if (verificar()) {
            
            this.incidencia = new IncidenciaTienda(this.textfieldTitulo.getText(),this.textfieldDescripcion.getText(),LocalDate.now());
                        
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        }
    }
    
    public void botonNuevo(){
        this.botonCrear.setVisible(true);
    }
    
    public void botonEditar(){
        this.botonEditar.setVisible(true);
    }

    private boolean verificar() {
        boolean devolver = true;
        if (this.textfieldDescripcion.getText().isEmpty()) {
            this.textfieldDescripcion.setStyle("-fx-border-color: red;");
            devolver = false;
        } else {
            this.textfieldDescripcion.setStyle("-fx-border-color: none;");
        }
        if(this.textfieldTitulo.getText().isEmpty()){
            this.textfieldTitulo.setStyle("-fx-border-color: red;");
            devolver = false;
        } else {
            this.textfieldTitulo.setStyle("-fx-border-color: none;");
        }
        return devolver;
    }
    
    public IncidenciaTienda cogerIncidencia(){
        return this.incidencia;
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionEditar(ActionEvent event) {
           guardarIncidencia();
    }

}
