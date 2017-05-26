/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tiendas.Editar;

import DATOS.GestionTiendas;
import MODELO.Alertas;
import MODELO.Listas.ListaTiendas;
import MODELO.Tienda;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class EditarTiendaController implements Initializable {

    Tienda tiendaAntigua;
    Tienda tiendaNueva;
    ListaTiendas lt;
    String boton = "cancelar";

    @FXML
    private TextField textID;
    @FXML
    private TextField textCiudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private TextField textDireccion;
    @FXML
    private Button botonEditar;
    @FXML
    private Button botonCancelar;
    @FXML
    private Label textoAviso;
    @FXML
    private Label labelID;
    @FXML
    private Label labelCiudad;
    @FXML
    private Label labelDireccion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void mostrarTienda() {
        this.textID.setText(String.valueOf(tiendaAntigua.getIdTienda()));
        this.textCiudad.setText(tiendaAntigua.getCiudad());
        this.textDireccion.setText(tiendaAntigua.getDireccion());
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        this.textoAviso.setText("");

        tiendaNueva = comprobarDatos(this.textID.getText(), this.textCiudad.getText(), this.textDireccion.getText());
        if (tiendaNueva != null) {
            if (!tiendaAntigua.igual(tiendaNueva)) {
                try {
                    lt.editarTienda(tiendaAntigua.getIdTienda(), tiendaNueva);
                    Alertas.generarAlerta("Tienda", "Tienda modificada sucessfully", Alert.AlertType.INFORMATION);
                    Stage stage = (Stage) this.botonEditar.getScene().getWindow();
                    boton = "Editar";
                    stage.close();
                } catch (SQLException e) {
                    Alertas.generarAlerta("BD", "Esa id ya esta asignada a una tienda, ponga otra", Alert.AlertType.ERROR);
                }
            } else {
                this.textoAviso.setText("- No se ha modificado ningún dato.");
            }
        }

    }

    private Tienda comprobarDatos(String id1, String ciudad, String direccion) {
        Tienda tienda = new Tienda();
        boolean seguir = true;
        Integer id = 0;
        try {
            id = Integer.parseInt(id1);
        } catch (NumberFormatException e) {
            this.textoAviso.setText("- El id solo debe contener números");
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
            tienda.setIdTienda(id);
            tienda.setCiudad(ciudad);
            tienda.setDireccion(direccion);
            return tienda;
        }
        return null;
    }

    @FXML
    private void accionCancelar(ActionEvent event
    ) {
        Stage stage = (Stage) this.botonEditar.getScene().getWindow();
        boton = "cancelar";
        stage.close();
    }
    
    public String getBoton(){
        return this.boton;
    }

    public Tienda getTiendaAntigua() {
        return tiendaAntigua;
    }

    public void setTiendaAntigua(Tienda tiendaAntigua) {
        this.tiendaAntigua = tiendaAntigua;
    }

    public Tienda getTiendaNueva() {
        return tiendaNueva;
    }

    public void setTiendaNueva(Tienda tiendaNueva) {
        this.tiendaNueva = tiendaNueva;
    }

    public ListaTiendas getListaTiendas() {
        return lt;
    }

    public void setLt(ListaTiendas listaTiendas) {
        this.lt = listaTiendas;
    }

}
