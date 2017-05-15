/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Jefe.Tiendas.Nueva;

import DATOS.ListaTiendas;
import MODELO.Tienda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class NuevaTiendaController implements Initializable {

    @FXML
    private Button botonCancelar;
    @FXML
    private TextField textDireccion;
    @FXML
    private TextField textCiudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private TextField textID;
    @FXML
    private Button botonNuevo;
    @FXML
    private Label problemas;
    @FXML
    private Label labelDireccion;
    @FXML
    private Label labelCiudad;
    @FXML
    private Label labelID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void accionCancelar(ActionEvent event) {
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        this.problemas.setText("");
        try {
            Integer id = Integer.parseInt(this.textID.getText());
            String direccion = this.textDireccion.getText();
            String ciudad = this.textCiudad.getText();
            if (id == null) {

            }
            if (ciudad == null) {

            }
            if (direccion == null) {

            }
            Tienda tienda = new Tienda();
            tienda.setIdTienda(id);
            tienda.setDireccion(direccion);
            tienda.setCiudad(ciudad);
            ListaTiendas.nuevaTienda(tienda);
        } catch (NumberFormatException ex) {
            this.problemas.setText("La id solo puede contener números");
        }

    }
    
    private void comprobarDatos(String id1,String direccion,String ciudad){
        
    }

}
