/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Trabajador.ModificarIncidencia;

import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Incidencia.IncidenciaTrabajador;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class ModificarIncidenciaController implements Initializable {

    private IncidenciaTrabajador incidencia;
    
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private TextArea textAreaDescripcion;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonEditar;
    @FXML
    private Button botonCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      @FXML
    private void accionCrear(ActionEvent event) throws IOException {
        this.incidencia = new IncidenciaTrabajador();
        guardarIncidencia();
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
    
    private void guardarIncidencia(){
        if (verificar()) {
            this.incidencia = new IncidenciaTrabajador(this.incidencia.getTipo(),this.incidencia.getIdIncidencia(),this.textFieldTitulo.getText(),this.textAreaDescripcion.getText(),LocalDate.now());
            
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        }
    }
    
    public void botonNuevo(){
        this.botonCrear.setVisible(true);
    }
    
    public void botonEditar(IncidenciaTrabajador incidencia){
        this.botonEditar.setVisible(true);
        this.incidencia = incidencia;
        this.textFieldTitulo.setText(this.incidencia.getTitulo());
        this.textAreaDescripcion.setText(this.incidencia.getDescripcion());
        
    }

    private boolean verificar() {
        boolean devolver = true;
        if (this.textAreaDescripcion.getText().isEmpty()) {
            this.textAreaDescripcion.setStyle("-fx-border-color: red;");
            devolver = false;
        } else {
            this.textAreaDescripcion.setStyle("-fx-border-color: none;");
        }
        if(this.textFieldTitulo.getText().isEmpty()){
            this.textFieldTitulo.setStyle("-fx-border-color: red;");
            devolver = false;
        } else {
            this.textFieldTitulo.setStyle("-fx-border-color: none;");
        }
        return devolver;
    }
    
    public IncidenciaTrabajador cogerIncidencia(){
        return this.incidencia;
    }
    
}
