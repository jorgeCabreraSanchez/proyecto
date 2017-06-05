/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Catalogo.Modificar;

import MODELO.Producto;
import java.net.URL;
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
public class CatalogoModificarController implements Initializable {

    Producto producto;

    @FXML
    private TextField textfieldNombre;
    @FXML
    private TextArea textAreaDescripcion;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button botonEditar;
    @FXML
    private Button botonCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void botonNuevo() {
        this.botonCrear.setVisible(true);
    }

    public void botonEditar(Producto producto) {
        this.botonEditar.setVisible(true);
        this.producto = producto;
        this.textfieldNombre.setText(producto.getNombre());
        this.textAreaDescripcion.setText(producto.getDescripcion());
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        if (verificar()) {
            producto.setNombre(this.textfieldNombre.getText());
            producto.setDescripcion(this.textAreaDescripcion.getText());
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void accionCrear(ActionEvent event) {
        if (verificar()) {
            producto = new Producto();
            producto.setNombre(this.textfieldNombre.getText());
            producto.setDescripcion(this.textAreaDescripcion.getText());
            Stage stage = (Stage) this.botonCancelar.getScene().getWindow();
            stage.close();
        }
    }

    public Producto cogerProducto() {
        return this.producto;
    }

    private boolean verificar() {
        boolean devolver = true;
        if (this.textfieldNombre.getText().isEmpty()) {
            this.textfieldNombre.setStyle("-fx-border-color: red;");
            devolver = false;
        } else {
            this.textfieldNombre.setStyle("-fx-border-color: none;");
        }
        if (this.textAreaDescripcion.getText().isEmpty()) {
            this.textAreaDescripcion.setStyle("-fx-border-color: red;");
            devolver = false;
        } else {
            this.textAreaDescripcion.setStyle("-fx-border-color: none;");
        }
        return devolver;
    }

}
