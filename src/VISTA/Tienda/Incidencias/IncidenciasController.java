/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Incidencias;

import MODELO.Alertas;
import MODELO.Incidencia.Incidencia;
import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Listas.ListaIncidenciasTienda;
import VISTA.Tienda.Incidencias.NuevaEditar.NuevaEditarIncidenciaController;
import VISTA.Tienda.Which.TiendaWhichController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private DatePicker datePickerDesde;
    @FXML
    private DatePicker datePickerHasta;
    @FXML
    private Button buttonNuevo;
    @FXML
    private Button buttonEliminar;
    @FXML
    private Button buttonEditar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setIDTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public void rellenarTabla() throws SQLException {
        this.lit = new ListaIncidenciasTienda(this.idTienda);
        this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias()));
    }

    private void actualizarIncidencias() {
        LocalDate fechaEntrada;
        LocalDate fechaSalida;
        if (this.datePickerDesde.getValue() == null) {
            fechaEntrada = LocalDate.MIN;
        } else {
            fechaEntrada = this.datePickerDesde.getValue();
        }
        if (this.datePickerHasta.getValue() == null) {
            fechaSalida = LocalDate.now();
        } else {
            fechaSalida = this.datePickerHasta.getValue();

        }
        this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias(fechaEntrada, fechaSalida)));
    }

    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Which/TiendaWhich.fxml"));
            Parent root = loader.load();
            TiendaWhichController controller = loader.getController();
            controller.setIDTienda(this.idTienda);

            Stage stage = (Stage) this.buttonVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Ver Tienda", "No se ha podido mostrar la ventana ver Tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void introducirFecha(ActionEvent event) {
        actualizarIncidencias();
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Incidencias/NuevaEditar/NuevaEditarIncidencia.fxml"));
            Parent root = loader.load();
            NuevaEditarIncidenciaController controller = loader.getController();
            controller.botonNuevo();

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.initModality(Modality.APPLICATION_MODAL);
            stageNuevo.showAndWait();

            IncidenciaTienda incidencia = controller.cogerIncidencia();
            if (incidencia != null) {
                incidencia.setIdTienda(this.idTienda);
                this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.nuevaIncidencia(incidencia)));
                actualizarIncidencias();
            }

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Nueva Incidencia", "No se ha podido abrir la ventana nueva incidencia", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido crear la incidencia", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        IncidenciaTienda incidencia = this.listViewIncidencias.getSelectionModel().getSelectedItem();
        if (incidencia != null) {
            Optional<ButtonType> boton = Alertas.generarAlerta("Tiendas", "Esta seguro que desea borra la Incidencia?", "Información de la Incidencia:  ID: " + incidencia.getIdIncidencia() + "   Titulo: " + incidencia.getTitulo() + " \n  Descripción: " + incidencia.getDescripcion(), AlertType.CONFIRMATION);
            if (boton.get().getText().equalsIgnoreCase("aceptar")) {
                try {
                    lit.borrarIncidencia(incidencia.getIdIncidencia());
                    actualizarIncidencias();
                } catch (SQLException e) {
                    Alertas.generarAlerta("Error BD", "Ha habido un error intentando borrar la incidencia y no se ha podido", "Error: " + e.getErrorCode() + " " + e.getLocalizedMessage(), AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        IncidenciaTienda incidenciaDar = this.listViewIncidencias.getSelectionModel().getSelectedItem();
        if (incidenciaDar != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/Tienda/Incidencias/NuevaEditar/NuevaEditarIncidencia.fxml"));
                Parent root = loader.load();
                NuevaEditarIncidenciaController controller = loader.getController();
                controller.botonEditar(incidenciaDar);

                Stage stageNuevo = new Stage();
                stageNuevo.setScene(new Scene(root));
                stageNuevo.initModality(Modality.APPLICATION_MODAL);
                stageNuevo.showAndWait();

                IncidenciaTienda incidencia = controller.cogerIncidencia();
                if (incidencia != null) {
                    incidencia.setIdTienda(this.idTienda);
                    this.listViewIncidencias.setItems(FXCollections.observableArrayList(lit.editarIncidencia(incidencia)));
                    actualizarIncidencias();
                }

            } catch (IOException ex) {
                Alertas.generarAlerta("Ventana Editar Incidencia", "No se ha podido abrir la ventana editar incidencia", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se ha podido modificar la incidencia", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        }
    }

}
