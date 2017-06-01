/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Incidencias;

import MODELO.Alertas;
import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Listas.ListaIncidenciasTienda;
import VISTA.Tienda.Which.TiendaWhichController;
import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDateTextField;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class IncidenciasController implements Initializable {

    private int idTienda;
    private ListaIncidenciasTienda lit;

    @FXML
    private Button buttonVolver;
    @FXML
    private ScrollPane scrollPaneIncidencias;
    @FXML
    private ListView<IncidenciaTienda> listViewIncidencias;
    @FXML
    private Label labelTienda;
    @FXML
    private LocalDateTextField textFieldDesde;
    @FXML
    private LocalDateTextField textFieldHasta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setIDTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public void rellenarTabla() throws SQLException {
        lit = new ListaIncidenciasTienda(this.idTienda);
        this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias()));
        fechas();
    }

    private void fechas() {
        this.textFieldDesde.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.textFieldHasta.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        this.textFieldDesde.addEventHandler(EventType<ActionEvent>, (event) -> {
            actualizarIncidencias();
        });
        
        this.textFieldHasta.addEventHandler(EventType.ROOT, (event) -> {
            actualizarIncidencias();
        });

    }

    private void actualizarIncidencias(){
        this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias(this.textFieldDesde.getLocalDate(),this.textFieldHasta.getLocalDate())));
    }
    
    
    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Which/TiendaWhich.fxml"));
            Parent root = loader.load();
            TiendaWhichController controller = loader.getController();
            controller.setIDTienda(this.idTienda);

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.show();

            Stage stage = (Stage) this.buttonVolver.getScene().getWindow();
            stage.close();

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Ver Tienda", "No se ha podido mostrar la ventana ver Tienda", Alert.AlertType.ERROR);
        }
    }



}
