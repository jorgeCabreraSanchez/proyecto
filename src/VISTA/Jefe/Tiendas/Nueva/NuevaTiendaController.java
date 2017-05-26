/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Jefe.Tiendas.Nueva;

import DATOS.GestionTiendas;
import MODELO.Alertas;
import MODELO.Listas.ListaTiendas;
import MODELO.Tienda;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class NuevaTiendaController implements Initializable {

    ListaTiendas lt;
    Button boton = new Button();

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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.botonCancelar.setUserData("Cancelar");
        this.botonNuevo.setUserData("Nuevo");
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        boton = this.botonCancelar;
        stage.close();
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        this.problemas.setText("");
        Tienda tienda = comprobarDatos(this.textID.getText(), this.textCiudad.getText(), this.textDireccion.getText());
        if (tienda != null) {
            try {
                lt.nuevaTienda(tienda);
                Alertas.generarAlerta("Tienda", "Tienda creada sucessfully", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
                boton = this.botonNuevo;
                stage.close();
            } catch (SQLException e) {
                Alertas.generarAlerta("BD", "Esa id esta asignada a una tienda, ponga otra diferente", Alert.AlertType.ERROR);
            }
        }

    }
    
    public Button getButton(){
        return this.boton;
    }

    private Tienda comprobarDatos(String id1, String ciudad, String direccion) {
        Tienda tienda = null;
        boolean seguir = true;
        Integer id = 0;
        try {
            id = Integer.parseInt(id1);
        } catch (NumberFormatException e) {
            this.problemas.setText("- El id solo debe contener números");
            seguir = false;
        }

        if (ciudad.isEmpty()) {
            this.labelCiudad.setText("Ciudad*");
            this.labelCiudad.setStyle("-fx-text-fill: #ff0000");
            seguir = false;
        } else {
            this.labelCiudad.setText("Ciudad:");
            this.labelCiudad.setStyle("-fx-text-fill: black");
        }
        if (id1.isEmpty()) {
            this.labelID.setText("ID*:");
            this.labelID.setStyle("-fx-text-fill: #ff0000");
            seguir = false;
        } else {
            this.labelID.setText("ID:");
            this.labelID.setStyle("-fx-text-fill: black");
        }
        if (direccion.isEmpty()) {
            this.labelDireccion.setText("Direccion*:");
            this.labelDireccion.setStyle("-fx-text-fill: #ff0000");
            seguir = false;
        } else {
            this.labelDireccion.setText("Direccion:");
            this.labelDireccion.setStyle("-fx-text-fill: black");
        }
        if (seguir) {
            tienda = new Tienda();
            tienda.setIdTienda(id);
            tienda.setCiudad(ciudad);
            tienda.setDireccion(direccion);
        }
        return tienda;
    }

    public ListaTiendas getLt() {
        return lt;
    }

    public void setLt(ListaTiendas lt) {
        this.lt = lt;
    }

}
