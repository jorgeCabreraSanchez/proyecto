package VISTA.Perfil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import MODELO.Alertas;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Trabajadores.TrabajadoresController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daw
 */
public class PerfilController implements Initializable {

    Trabajadores trabajador;
    int idTienda;

    @FXML
    private Pane foto;
    @FXML
    private Label nombre;
    @FXML
    private Label tipoEmpleado;
    @FXML
    private Button botonIncidencia;
    @FXML
    private Button botonCalendario;
    @FXML
    private Label tienda;
    @FXML
    private Button buttonVolver;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Trabajadores/Trabajadores.fxml"));
            Parent root = loader.load(); 
            TrabajadoresController controller = loader.getController();
            controller.setIDTienda(this.idTienda);
            controller.mostrarTrabajadores();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            Stage stageActual = (Stage) this.tienda.getScene().getWindow();
            stageActual.close();
        } catch (IOException ex) {
            Alertas.generarAlerta("Lista Trabajadores", "No se ha podido volver a la lista de usuarios", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido volver a la lista de usuarios", Alert.AlertType.ERROR);
        }
    }
    
    public void modoJefe(){
        this.buttonVolver.setVisible(true);
    }

    public void setTrabajador(Trabajadores trabajador,int idTienda) {
        this.trabajador = trabajador;
        this.idTienda = idTienda;
    }

}
