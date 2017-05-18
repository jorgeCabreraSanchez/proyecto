/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Jefe.Tiendas.Editar;

import DATOS.GestionTiendas;
import MODELO.Listas.ListaTiendas;
import MODELO.Tienda;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
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
        tiendaNueva = new Tienda(Integer.parseInt(this.textID.getText()), this.textCiudad.getText(), this.textDireccion.getText());
        if (!tiendaAntigua.igual(tiendaNueva)) {
            lt.editarTienda(tiendaAntigua, tiendaNueva);
        }        
        Stage stage = (Stage) this.botonEditar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        Stage stage = (Stage) this.botonEditar.getScene().getWindow();
        stage.close();
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
